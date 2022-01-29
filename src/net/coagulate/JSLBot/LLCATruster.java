package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * The badly implemented SSL verifier for the Linden Labs CA.
 *
 * @author Iain Price
 */
public class LLCATruster implements X509TrustManager, HostnameVerifier {

	private static final String certificate= // this is the Linden Labs internal CA and thus not in any typical trusted package.
			"-----BEGIN CERTIFICATE-----\n"+  // we cludge it all together here to avoid anyone having to care about keystores and stuff
					"MIIEUDCCA7mgAwIBAgIJAN4ppNGwj6yIMA0GCSqGSIb3DQEBBAUAMIHMMQswCQYD\n"+"VQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZyYW5j\n"+"aXNjbzEZMBcGA1UEChMQTGluZGVuIExhYiwgSW5jLjEpMCcGA1UECxMgTGluZGVu\n"+"IExhYiBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxKTAnBgNVBAMTIExpbmRlbiBMYWIg\n"+"Q2VydGlmaWNhdGUgQXV0aG9yaXR5MR8wHQYJKoZIhvcNAQkBFhBjYUBsaW5kZW5s\n"+"YWIuY29tMB4XDTA1MDQyMTAyNDAzMVoXDTI1MDQxNjAyNDAzMVowgcwxCzAJBgNV\n"+"BAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1TYW4gRnJhbmNp\n"+"c2NvMRkwFwYDVQQKExBMaW5kZW4gTGFiLCBJbmMuMSkwJwYDVQQLEyBMaW5kZW4g\n"+"TGFiIENlcnRpZmljYXRlIEF1dGhvcml0eTEpMCcGA1UEAxMgTGluZGVuIExhYiBD\n"+"ZXJ0aWZpY2F0ZSBBdXRob3JpdHkxHzAdBgkqhkiG9w0BCQEWEGNhQGxpbmRlbmxh\n"+"Yi5jb20wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKXh1MThucdTbMg9bYBO\n"+"rAm8yWns32YojB0PRfbq8rUjepEhTm3/13s0u399Uc202v4ejcGhkIDWJZd2NZMF\n"+"oKrhmRfxGHSKPCuFaXC3jh0lRECj7k8FoPkcmaPjSyodrDFDUUuv+C06oYJoI+rk\n"+"8REyal9NwgHvqCzOrZtiTXAdAgMBAAGjggE2MIIBMjAdBgNVHQ4EFgQUO1zK2e1f\n"+"1wO1fHAjq6DTJobKDrcwggEBBgNVHSMEgfkwgfaAFDtcytntX9cDtXxwI6ug0yaG\n"+"yg63oYHSpIHPMIHMMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEW\n"+"MBQGA1UEBxMNU2FuIEZyYW5jaXNjbzEZMBcGA1UEChMQTGluZGVuIExhYiwgSW5j\n"+"LjEpMCcGA1UECxMgTGluZGVuIExhYiBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxKTAn\n"+"BgNVBAMTIExpbmRlbiBMYWIgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MR8wHQYJKoZI\n"+"hvcNAQkBFhBjYUBsaW5kZW5sYWIuY29tggkA3imk0bCPrIgwDAYDVR0TBAUwAwEB\n"+"/zANBgkqhkiG9w0BAQQFAAOBgQA/ZkgfvwHYqk1UIAKZS3kMCxz0HvYuEQtviwnu\n"+"xA39CIJ65Zozs28Eg1aV9/Y+Of7TnWhW+U3J3/wD/GghaAGiKK6vMn9gJBIdBX/9\n"+"e6ef37VGyiOEFFjnUIbuk0RWty0orN76q/lI/xjCi15XSA/VSq2j4vmnwfZcPTDu\n"+"glmQ1A==\n"+"-----END CERTIFICATE-----";
	private static X509Certificate[] cas;
	@Nonnull
	private static Boolean initialised=false;

	public LLCATruster() {
		try {
			// SSL connections to the SL service use a CA signed and held by Linden Labs.
			// rather than have the user repeatedly fudge around with updating the keystore every time java updates (i hate that)
			// we just use our own customised trust manager
			// long winded java way of faking reading the cert from a stream
			@Nonnull final InputStream stream=new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8));
			@Nonnull final X509Certificate ca=(X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(stream);
			cas=new X509Certificate[]{ca};
			// we're a TLS handler
			@Nonnull final SSLContext sc=SSLContext.getInstance("TLS");
			sc.init(null,new TrustManager[]{this},new java.security.SecureRandom());
			// install
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		catch (@Nonnull final CertificateException|KeyManagementException|NoSuchAlgorithmException ex) {
			throw new AssertionError("Error configuring SSL CA Trust",ex);
		}
	}

	// ---------- STATICS ----------

	/**
	 * Do not use the JSLBot LLCA Truster.  You will probably get SSL errors if you don't implement this yourself somehow.
	 */
	public static void doNotUse() { initialised=true; }

	public static synchronized void initialise() {
		if (initialised) { return; }
		@Nonnull final LLCATruster llcaTruster=new LLCATruster();
		HttpsURLConnection.setDefaultHostnameVerifier(llcaTruster);
		initialised=true;
	}

	// ---------- INSTANCE ----------
	@Override
	public boolean verify(final String string,
	                      @Nonnull final SSLSession ssls) {
		//throw new AssertionError("Verify for "+string+" called with session "+ssls);
		return true;
	}

	@Override
	public void checkClientTrusted(final X509Certificate[] xcs,
	                               final String string) {
		throw new AssertionError("CheckClientTrusted called in LLCATruster");
	}

	@Override
	public void checkServerTrusted(final X509Certificate[] xcs,
	                               final String string) {
		// FIXME
		//System.out.println("Cert len:"+xcs.length);
		//System.out.println("Random string:"+string);
		//throw new AssertionError("CheckServerTrusted called in LLCATruster");
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return cas;
	}

}

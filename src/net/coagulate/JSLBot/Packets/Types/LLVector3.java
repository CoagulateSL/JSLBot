package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class LLVector3 extends Type{

    public static LLVector3 random() {
        LLVector3 v=new LLVector3();
        v.x=(float) (Math.random()*10.0-5.0);
        v.y=(float) (Math.random()*10.0-5.0);
        v.z=(float) (Math.random()*10.0-5.0);
        return v;
    }
    public float x=0;
    public float y=0;
    public float z=0;

    public LLVector3(ByteBuffer buffer) {
        read(buffer);
    }

    public LLVector3(String pos) {
        pos=pos.replaceAll("<","");
        pos=pos.replaceAll(">","");
        String[] comps = pos.split(",");
        x=Float.parseFloat(comps[0]);
        y=Float.parseFloat(comps[1]);
        z=Float.parseFloat(comps[2]);
    }
    @Override
    public int size() {
        return 12;
    }
    public LLVector3(){}
    public LLVector3(float x,float y,float z) {
        this.x=x; this.y=y; this.z=z;
    }
    public LLVector3(String x,String y,String z){
        this.x=Float.parseFloat(x);
        this.y=Float.parseFloat(y);
        this.z=Float.parseFloat(z);
    }

    @Override
    public void read(ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        x=in.getFloat();
        y=in.getFloat();
        z=in.getFloat();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putFloat(x);
        out.putFloat(y);
        out.putFloat(z);
    }

    @Override
    public String dump() {
        return "<"+x+","+y+","+z+">";
    }
       
}

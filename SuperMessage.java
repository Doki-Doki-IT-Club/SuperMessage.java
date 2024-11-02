public class SuperMessage
{
    private long smsg = 0;

	public SuperMessage(int type) {
		smsg = smCreate(type);
	}

	protected void finalize() {
		smDestroy(smsg);
	}

    public int getSize() {
		return smGetSize(smsg);
	}

    public int getType() {
		return smGetType(smsg);
	}

    public long sizeByType() {
		return smSizeByType(smsg);
	}

	static {
		System.loadLibrary("smsg-jni");
	}

	private static native long smCreate(int start);
	private static native void smDestroy(long ptr);
    private static native int smGetSize(long ptr);
    private static native int smGetType(long ptr);
    private static native long smSizeByType(long ptr);
}

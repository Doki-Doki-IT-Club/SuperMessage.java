public class SuperMessage
{
    private long smsg = 0;

	public SuperMessage(int type) {
		smsg = smCreate(type);
	}

	public SuperMessage(String typeName) {
        int type = smParseTypeNum(typeName);
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

    public int getInt(int fieldNum) {
        return smIntPT(smGetValue(smsg, fieldNum));
    }

    public String getString(int fieldNum) {
        return smStringPT(smGetValue(smsg, fieldNum));
    }

    public void setValue(int fieldNum, int value) {
        smSetIntValue(smsg, fieldNum, smIntTP(value));
    }

    public void setValue(int fieldNum, boolean value) {
        if (value == true) { smSetIntValue(smsg, fieldNum, smIntTP(1)); return; }
        if (value == false) { smSetIntValue(smsg, fieldNum, smIntTP(0)); return; }
    }

    public void setValue(int fieldNum, String value) {
        smSetStringValue(smsg, fieldNum, value);
    }

    public int getInt(String fieldName) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        return smIntPT(smGetValue(smsg, fieldNum));
    }

    public String getString(String fieldName) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        return smStringPT(smGetValue(smsg, fieldNum));
    }

    public void setValue(String fieldName, int value) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        smSetIntValue(smsg, fieldNum, smIntTP(value));
    }

    public void setValue(String fieldName, boolean value) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        if (value == true) { smSetIntValue(smsg, fieldNum, smIntTP(1)); return; }
        if (value == false) { smSetIntValue(smsg, fieldNum, smIntTP(0)); return; }
    }

    public void setValue(String fieldName, String value) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        smSetStringValue(smsg, fieldNum, value);
    }

	static {
		System.loadLibrary("smsg-jni");
	}

	private static native long smCreate(int type);
	private static native void smDestroy(long msg);
    private static native int smGetSize(long msg);
    private static native int smGetType(long msg);
    private static native long smGetValue(long msg, int fieldNum);
    private static native long smSizeByType(long msg);
    private static native void smSetIntValue(long msg, int fieldNum, long value);
    private static native void smSetStringValue(long msg, int fieldNum, String value);
    private static native int smParseFieldNum(long msg, String fieldName);
    private static native int smParseTypeNum(String fieldName);
    private static native long smIntTP(int value);
    private static native int smIntPT(long value);
    private static native String smStringPT(long value);

    public class smService {

        private long sm = 0;
        public bExit b_exit;
    
        public smService() {
            b_exit = new bExit();
            sm = smCreate(1);
            smSizeByType(sm);
        }    
    
        protected void finalize() {
            smDestroy(sm);
        }
    
        public class bExit {
            public boolean get()
            {
                int tmp = smIntPT(smGetValue(sm, 0));
                if (tmp == 0) { return false; }
                else { return true; }
            }
            public void set(boolean newValue)
            {
                if (newValue == false)
                { smSetIntValue(sm, 0, smIntTP(0)); }
                else { smSetIntValue(sm, 0, smIntTP(1)); }
            }
        }
    }
}

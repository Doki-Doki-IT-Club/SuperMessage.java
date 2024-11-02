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

    public int getInt(int fieldNum) {
        return smIntPT(smGetValue(smsg, fieldNum));
    }

    public void setValue(int fieldNum, int value) {
        smSetIntValue(smsg, fieldNum, smIntTP(value));
    }

    public void setValue(int fieldNum, boolean value) {
        if (value == true) { smSetIntValue(smsg, fieldNum, smIntTP(1)); return; }
        if (value == false) { smSetIntValue(smsg, fieldNum, smIntTP(0)); return; }
    }

    public void setValue(int fieldNum, String value) {
    }

	static {
		System.loadLibrary("smsg-jni");
	}

	private static native long smCreate(int start);
	private static native void smDestroy(long msg);
    private static native int smGetSize(long msg);
    private static native int smGetType(long msg);
    private static native long smGetValue(long msg, int fieldNum);
    private static native long smSizeByType(long msg);
    private static native void smSetIntValue(long msg, int fieldNum, long value);
    private static native void smSetStringValue(long msg, int fieldNum, String value);
    private static native int smParseFieldNum(long msg, long fieldName);
    private static native long smIntTP(int value);
    private static native int smIntPT(long value);

    public enum Types {
        SM(0),
        SERVICE(1),
        MESSAGE(2),
        CLIENT_INIT(3),
        USERS_ONLINE(4);

        private final int num;

        Types(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public enum SM_f {
            SIZE(0),
            TYPE(0);

            private final int fieldNum;
            SM_f(int fieldNum) { this.fieldNum = fieldNum; }
            public int getFieldNum() { return fieldNum; }
        }

        public enum SERVICE_f {
            B_EXIT(0),
            B_REBOOT(1);

            private final int fieldNum;
            SERVICE_f(int fieldNum) { this.fieldNum = fieldNum; }
            public int getFieldNum() { return fieldNum; }
        }

        public enum MESSAGE_f {
            B_GLOBAL(0),
            B_SELF(1),
            CLIENT_ID(2),
            CLIENT_NAME(3),
            TEXT(4),
            TEXT_LEN(5);

            private final int fieldNum;
            MESSAGE_f(int fieldNum) { this.fieldNum = fieldNum; }
            public int getFieldNum() { return fieldNum; }
        }

        public enum CLIENT_INIT_f {
            SELF_ID(0);

            private final int fieldNum;
            CLIENT_INIT_f(int fieldNum) { this.fieldNum = fieldNum; }
            public int getFieldNum() { return fieldNum; }
        }

        public enum USERS_ONLINE_f {
            NAMES(0),
            USERS_COUNT(0);

            private final int fieldNum;
            USERS_ONLINE_f(int fieldNum) { this.fieldNum = fieldNum; }
            public int getFieldNum() { return fieldNum; }
        }
    }

    public SuperMessage(SuperMessage.Types type) {}
    
    public SuperMessage(SuperMessage.Types.SM_f fields) {}
}

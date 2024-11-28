public class SuperMessage
{
    private long smsg = 0;

    public SuperMessage() {}

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

    protected int getSize() {
        return smGetSize(smsg);
    }

    protected int getType() {
        return smGetType(smsg);
    }

    protected long sizeByType() {
        return smSizeByType(smsg);
    }

    protected void setValue(int fieldNum, int value, int index) {
        smSetIntValue(smsg, fieldNum, smIntTP(value), index);
    }

    protected void setValue(int fieldNum, boolean value, int index) {
        if (value == true) { smSetIntValue(smsg, fieldNum, smIntTP(1), index); return; }
        if (value == false) { smSetIntValue(smsg, fieldNum, smIntTP(0), index); return; }
    }

    protected void setValue(int fieldNum, String value, int index) {
        smSetStringValue(smsg, fieldNum, value, index);
    }

    protected void setValue(String fieldName, int value, int index) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        smSetIntValue(smsg, fieldNum, smIntTP(value), index);
    }

    protected void setValue(String fieldName, boolean value, int index) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        if (value == true) { smSetIntValue(smsg, fieldNum, smIntTP(1), index); return; }
        if (value == false) { smSetIntValue(smsg, fieldNum, smIntTP(0), index); return; }
    }

    protected void setValue(String fieldName, String value, int index) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        smSetStringValue(smsg, fieldNum, value, index);
    }

    protected int getInt(String fieldName, int index) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        return smIntPT(smGetValue(smsg, fieldNum, index));
    }

    protected int getInt(int fieldNum, int index) {
        return smIntPT(smGetValue(smsg, fieldNum, index));
    }

    protected String getString(String fieldName, int index) {
        int fieldNum = smParseFieldNum(smsg, fieldName);
        return smStringPT(smGetValue(smsg, fieldNum, index));
    }

    protected String getString(int fieldNum, int index) {
        return smStringPT(smGetValue(smsg, fieldNum, index));
    }

    static {
        System.loadLibrary("jni-smsg");
    }

    private static native long smCreate(int type);
    private static native void smDestroy(long msg);
    private static native int smGetSize(long msg);
    private static native int smGetType(long msg);
    private static native long smGetValue(long msg, int fieldNum, int index);
    private static native long smSizeByType(long msg);
    private static native void smSetIntValue(long msg, int fieldNum, long value, int index);
    private static native void smSetStringValue(long msg, int fieldNum, String value, int index);
    private static native int smParseFieldNum(long msg, String fieldName);
    private static native int smParseTypeNum(String fieldName);
    private static native long smIntTP(int value);
    private static native int smIntPT(long value);
    private static native String smStringPT(long value);

    public class smService {

        private long sm = 0;
        public bExit b_exit;
        public bReboot b_reboot;

        public smService() {
            b_exit = new bExit();
            b_reboot = new bReboot();
            sm = smCreate(1);
            smSizeByType(sm);
        }

        protected void finalize() {
            smDestroy(sm);
        }

        public int getSize() {
            return smGetSize(sm);
        }

        public int getType() {
            return smGetType(sm);
        }

        public class bExit {
            public boolean get()
            {
                long tmp = smIntPT(smGetValue(sm, 0, 0));
                if (tmp == 0) { return false; }
                else { return true; }
            }
            public void set(boolean newValue)
            {
                if (newValue == false)
                { smSetIntValue(sm, 0, smIntTP(0), 0); }
                else { smSetIntValue(sm, 0, smIntTP(1), 0); }
            }
        }

        public class bReboot {
            public boolean get()
            {
                long tmp = smIntPT(smGetValue(sm, 1, 0));
                if (tmp == 0) { return false; }
                else { return true; }
            }
            public void set(boolean newValue)
            {
                if (newValue == false)
                { smSetIntValue(sm, 1, smIntTP(0), 0); }
                else { smSetIntValue(sm, 1, smIntTP(1), 0); }
            }
        }
    }

    public class smMessage {
        private long sm = 0;
        public bGlobal b_global;
        public bSelf b_self;
        public clientId client_id;
        public clientName client_name;
        public textClass text;
        public textLen text_len;

        public smMessage() {
            b_global = new bGlobal();
            b_self = new bSelf();
            client_id = new clientId();
            client_name = new clientName();
            text = new textClass();
            text_len = new textLen();
            sm = smCreate(2);
            smSizeByType(sm);
        }

        protected void finalize() {
            smDestroy(sm);
        }

        public int getSize() {
            return smGetSize(sm);
        }

        public int getType() {
            return smGetType(sm);
        }

        public class bGlobal {
            public boolean get()
            {
                long tmp = smIntPT(smGetValue(sm, 0, 0));
                if (tmp == 0) { return false; }
                else { return true; }
            }
            public void set(boolean newValue)
            {
                if (newValue == false)
                { smSetIntValue(sm, 0, smIntTP(0), 0); }
                else { smSetIntValue(sm, 0, smIntTP(1), 0); }
            }
        }

        public class bSelf {
            public boolean get()
            {
                long tmp = smIntPT(smGetValue(sm, 1, 0));
                if (tmp == 0) { return false; }
                else { return true; }
            }
            public void set(boolean newValue)
            {
                if (newValue == false)
                { smSetIntValue(sm, 1, smIntTP(0), 0); }
                else { smSetIntValue(sm, 1, smIntTP(1), 0); }
            }
        }

        public class clientId {
            public int get()
            { return smIntPT(smGetValue(sm, 2, 0)); }
            public void set(int newValue)
            { smSetIntValue(sm, 2, smIntTP(newValue), 0); }
        }

        public class clientName {
            public String get()
            { return smStringPT(smGetValue(sm, 3, 0)); }
            public void set(String newValue)
            { smSetStringValue(sm, 3, newValue, 0); }
        }

        public class textClass {
            public String get()
            { return smStringPT(smGetValue(sm, 4, 0)); }
            public void set(String newValue)
            { smSetStringValue(sm, 4, newValue, 0); }
        }

        public class textLen {
            public int get()
            { return smIntPT(smGetValue(sm, 5, 0)); }
            public void set(int newValue)
            { smSetIntValue(sm, 5, smIntTP(newValue), 0); }
        }
    }

    public class smClientInit {
        public selfId self_id;
        private long sm = 0;

        public smClientInit() {
            self_id = new selfId();
            sm = smCreate(3);
            smSizeByType(sm);
        }

        protected void finalize() {
            smDestroy(sm);
        }

        public int getSize() {
            return smGetSize(sm);
        }

        public int getType() {
            return smGetType(sm);
        }

        public class selfId {
            public int get()
            { return smIntPT(smGetValue(sm, 0, 0)); }
            public void set(int newValue)
            { smSetIntValue(sm, 0, smIntTP(newValue), 0); }
        }
    }

    public class smUsersOnline {
        public namesClass names;
        public usersCount users_count;
        private long sm = 0;

        public smUsersOnline() {
            names = new namesClass();
            users_count = new usersCount();
            sm = smCreate(4);
            smSizeByType(sm);
        }

        protected void finalize() {
            smDestroy(sm);
        }

        public int getSize() {
            return smGetSize(sm);
        }

        public int getType() {
            return smGetType(sm);
        }

        public class namesClass {
            public String get(int index)
            { return smStringPT(smGetValue(sm, 0, index)); }
            public void set(String newValue, int index)
            { smSetStringValue(sm, 0, newValue, index); }
        }

        public class usersCount {
            public int get()
            { return smIntPT(smGetValue(sm, 0, 0)); }
            public void set(int newValue)
            { smSetIntValue(sm, 0, smIntTP(newValue), 0); }
        }
    }
}

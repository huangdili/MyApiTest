package utils;

public class FrameworkException extends Exception {

        private static final long serialVersionUID = 1L;

        public FrameworkException() {}

        public FrameworkException(Exception ex) {
            super(ex.getMessage().startsWith("FrameworkException") ? ex.getMessage()
                    : "FrameworkException happened in " + Thread.currentThread().getStackTrace()[2]
                    + " and error message is: " + ex.getMessage());
        }

        public FrameworkException(String errorMsg) {
            super("FrameworkException happened in " + Thread.currentThread().getStackTrace()[2] + " and error message is: "
                    + errorMsg);
        }
}

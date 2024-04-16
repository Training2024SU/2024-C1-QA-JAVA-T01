package Garcia.Juan.util;


    public enum Roles {
        TIPO_UNO("ADMINISTRADOR"),TIPO_DOS("ASISTENTE"),TIPO_TRES("LECTOR");

        private String value;

        private Roles (String roles){
            this.value = roles;
        }

        public String getvalue() {
            return value;
        }
    }


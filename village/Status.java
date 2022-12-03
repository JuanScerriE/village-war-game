package village;

public enum Status {
    SUCCESS {
        @Override
        public void printMessage() {
            System.out.println("Success!");
        }
    },
    NO_EMPTY_ARMY {
        @Override
        public void printMessage() {
            System.out.println("No empty armies!");
        }
    },
    NOT_ENOUGH_RESOURCES {
        @Override
        public void printMessage() {
            System.out.println("Not enough resources!");
        }
    },
    NOT_ENOUGH_TROOPS {
        @Override
        public void printMessage() {
            System.out.println("Not enough troops!");
        }
    },
    NO_BUILDINGS {
        @Override
        public void printMessage() {
            System.out.println("No buildings!");
        }
    },
    SOME_TROOPS_CANNOT_BE_TRAINED {
        @Override
        public void printMessage() {
            System.out.println("Some troops cannot be trained further!");
        }
    },
    REACHED_MAXIMUM_LEVEL {
        @Override
        public void printMessage() {
            System.out.println("Reached maximum level!");
        }
    };

    public abstract void printMessage();
}

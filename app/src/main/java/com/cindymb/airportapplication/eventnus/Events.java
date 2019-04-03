package com.cindymb.airportapplication.eventnus;

public class Events {

    public static abstract class E {
        // Empty
    }

    public static class ConnectedEvent extends E {
        Boolean isConnected;

        public ConnectedEvent(Boolean isConnected) {
            this.isConnected = isConnected;
        }

        public Boolean getConnected() {
            return isConnected;
        }

        public void setConnected(Boolean connected) {
            isConnected = connected;
        }
    }
}

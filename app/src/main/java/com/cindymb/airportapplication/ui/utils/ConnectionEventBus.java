package com.cindymb.airportapplication.ui.utils;

public class ConnectionEventBus {

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

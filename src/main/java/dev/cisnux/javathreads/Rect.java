package dev.cisnux.javathreads;

public record Rect(double height, double width, double area) {

    Rect(double height, double width) {
        this(height, width, 0);
    }

    public static class Builder {
        public Builder setMutableHeight(double mutableHeight) {
            this.mutableHeight = mutableHeight;
            return this;
        }

        public Builder setMutableWidth(double mutableWidth) {
            this.mutableWidth = mutableWidth;
            return this;
        }

        public Builder setMutableArea(double mutableArea) {
            this.mutableArea = mutableArea;
            return this;
        }

        private double mutableHeight;
        private double mutableWidth;


        private double mutableArea;

        Builder(Rect rect) {
            this.mutableHeight = rect.height;
            this.mutableWidth = rect.width;
            this.mutableArea = rect.area;
        }

        Rect build() {
            return new Rect(this.mutableHeight, this.mutableWidth, this.mutableArea);
        }
    }

    Builder copy() {
        return new Builder(this);
    }
}

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Body (double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body (Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance (Body b) {
        return Math.sqrt(Math.pow(Math.abs(xxPos-b.xxPos),2) + Math.pow(Math.abs(yyPos-b.yyPos),2));
    }

    public double calcForceExertedBy(Body b) {
        return G * mass * b.mass / Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX (Body b) {
        return calcForceExertedBy(b) * (b.xxPos-xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY (Body b) {
        return calcForceExertedBy(b) * (b.yyPos-yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX (Body[] bodies) {
        double netForce = 0;
        for (Body body: bodies) {
            if (this.equals(body)) {
                continue;
            }
            netForce += calcForceExertedBy(body) * (body.xxPos-xxPos) / calcDistance(body);
        }
        return netForce;
    }

    public double calcNetForceExertedByY (Body[] bodies) {
        double netForce = 0;
        for (Body body: bodies) {
            if (this.equals(body)) {
                continue;
            }
            netForce += calcForceExertedBy(body) * (body.yyPos-yyPos) / calcDistance(body);
        }
        return netForce;
    }

    public void update (double time, double forceX, double forceY) {
        double accelerationX = forceX / mass;
        double accelerationY = forceY / mass;
        xxVel += accelerationX * time;
        yyVel += accelerationY * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
        return;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}

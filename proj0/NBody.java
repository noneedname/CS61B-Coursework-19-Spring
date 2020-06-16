public class NBody {
    public static double readRadius (String fileName) {
        In in = new In(fileName);
        in.readDouble();
        return in.readDouble();
    }

    public static Body[] readBodies (String fileName) {
        In in = new In(fileName);
        int num_planets = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[num_planets];
        for (int i=0; i<num_planets; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
//            System.out.println(body.xxPos + " " + body.mass + " " + body.imgFileName);
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int num_planets = 5;

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        for (double i=0; i<T; i+=dt) {
            double[] xForces = new double[num_planets];
            double[] yForces = new double[num_planets];

            for (int j=0; j<num_planets; j++) {
                xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
            }
            for (int z=0; z<num_planets; z++) {
                bodies[z].update(dt,xForces[z],yForces[z]);
            }

            StdDraw.picture(-0, 0, "images/starfield.jpg");
            for (Body body: bodies) {
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}

package render.scene;

import render.Ray3D;
import render.geometry.Shape;
import render.utils.ColorRGB;
import render.utils.Vec3f;

import java.util.ArrayList;

public abstract class Scene {

    protected final ArrayList<Shape> shapes;
    protected final ArrayList<Ray3D> lights;

    protected Scene() {
        this.shapes = new ArrayList<>();
        this.lights = new ArrayList<>();
    }

    public ColorRGB findColor(Vec3f origin, Vec3f direction, int maxRayDepth) {
        // Base case for recursion (max ray depth reached)
        if(maxRayDepth == 0)
            return Ray3D.AMBIENT_RAY;

        double minIntersectedDistance = Double.MAX_VALUE;
        Shape closestIntersectedShape = null;

        // Find the closest intersected shape in the scene
        for(Shape shape : this.shapes) {
            double intersectedDistance = shape.getIntersection(origin, direction);

            // Update the closest shape if a closer intersection is found
            if(intersectedDistance > 0.0D && intersectedDistance < minIntersectedDistance) {
                minIntersectedDistance = intersectedDistance;
                closestIntersectedShape = shape;
            }
        }

        // No intersection found, return black (background)
        if(closestIntersectedShape == null)
            return ColorRGB.black;

        // Calculate intersection point and normal at the intersection
        Vec3f intersectionPoint = closestIntersectedShape.getIntersectionPoint(origin, direction, minIntersectedDistance);
        Vec3f normalIntersection = closestIntersectedShape.getNormal(intersectionPoint);

        // Check if the ray is inside the shape (invert normal if true)
        boolean isInside = normalIntersection.dotProduct(direction) > 0.0D;
        if(isInside){
            normalIntersection = normalIntersection.scale(-1.0D);
        }

        // Initialize color with ambient light
        ColorRGB color = closestIntersectedShape.getColor().multiply(Ray3D.AMBIENT_RAY);

        // Normalize the normal and direction vectors
        normalIntersection = normalIntersection.normalize();
        direction = direction.normalize();

        // Iterate over all light sources to calculate lighting
        for(Ray3D light : this.lights) {
            Vec3f lightDirection = light.getPos().sub(intersectionPoint).normalize();

            boolean isVisible = true;
            double epsilon = 1e-6; // Small value to avoid precision issues for shadow calculation

            // Check if any shape blocks the light (shadow check)
            for(Shape shape : this.shapes) {
                double intersectionDistance = shape.getIntersection(intersectionPoint, lightDirection);

                // If there’s an intersection, the point is in shadow
                if(epsilon < intersectionDistance && intersectionDistance < 1.0D) {
                    isVisible = false;
                    break; // No need to check further if blocked
                }
            }

            // If light is visible (not blocked), calculate diffuse and specular lighting
            if(isVisible) {
                // Diffuse component (Lambertian shading)
                double dotProductNormalLight = Math.max(normalIntersection.dotProduct(lightDirection), 0.0D);

                // Specular reflection component (Phong model)
                Vec3f reflectionDirection = lightDirection.sub(normalIntersection.scale(2.0D * dotProductNormalLight));
                double dotProductReflectionRay = Math.max(reflectionDirection.dotProduct(direction), 0.0D);

                // Specular light intensity
                ColorRGB specular = light.getSpec().multiply(closestIntersectedShape.getSpecular())
                        .multiply(Math.pow(dotProductReflectionRay, closestIntersectedShape.getShininess()));

                // Diffuse light intensity
                ColorRGB diffuse = light.getDiff().multiply(closestIntersectedShape.getColor()).multiply(dotProductNormalLight);

                // Combine diffuse and specular components to the final color
                color = color.add(diffuse).add(specular);
            }
        }

        return color;
    }
}

package render.utils;

/**
 * Basic class to represent 3-vectors
 * Not intended to be a complete algebra class !
 * @author Philippe Meseure
 * @version 1.0
 */
public class Vec3f
{
    /**
     * x, y and z values of the current vector.
     * These are public to allow fast access and simple use.
     */
    public double x,y,z;

    /**
     * Default Constructor
     */
    public Vec3f()
    {
        this.x=this.y=this.z=0.F;
    }

    /**
     * Constructor with initialisation
     * @param x
     * @param y
     * @param z
     */
    public Vec3f(final double x,final double y,final double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * Constructor by copy
     * @param that vector to be copied in current vector
     */
    public Vec3f(final Vec3f that)
    {
        this.x=that.x;
        this.y=that.y;
        this.z=that.z;
    }

    /**
     * Set current vector's value to 0.0
     * @return new vector with value 0
     */
    public Vec3f reset()
    {
        return new Vec3f(0.F, 0.F, 0.F);
    }

    /**
     * Copy "that" vector in current vector and return a new vector
     * @param that vector to be copied
     * @return new vector with values of "that"
     */
    public Vec3f set(final Vec3f that)
    {
        return new Vec3f(that.x, that.y, that.z);
    }

    /**
     * Copy x, y and z in current vector and return a new vector
     * @param x,y,z values to place into new vector
     * @return new vector with values (x, y, z)
     */
    public Vec3f set(final double x,final double y,final double z)
    {
        return new Vec3f(x, y, z);
    }

    /**
     * @return square of the length of current vector
     */
    public double lengthSquare()
    {
        return this.x*this.x+this.y*this.y+this.z*this.z;
    }
    /**
     * @return length of current vector
     */
    public double length()
    {
        return (double)Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
    }

    /**
     * Normalize current vector and return a new normalized vector
     * @return new normalized vector
     */
    public Vec3f normalize()
    {
        double l=this.lengthSquare();
        if (l==0.F) return this;
        l=(double)Math.sqrt(l);
        return new Vec3f(this.x / l, this.y / l, this.z / l);
    }

    /**
     * Add a vector to current vector and return new vector
     * @param that any vector
     * @return new vector after addition
     */
    public Vec3f add(final Vec3f that)
    {
        return new Vec3f(this.x + that.x, this.y + that.y, this.z + that.z);
    }

    /**
     * Add two vectors v1 and v2 and return new vector
     * @param v1 any vector
     * @param v2 any vector
     * @return new vector after addition
     */
    public Vec3f setAdd(final Vec3f v1, final Vec3f v2)
    {
        return new Vec3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    /**
     * Subtract a vector from current vector and return new vector
     * @param that vector to subtract
     * @return new vector after subtraction
     */
    public Vec3f sub(final Vec3f that)
    {
        return new Vec3f(this.x - that.x, this.y - that.y, this.z - that.z);
    }

    /**
     * Subtract two vectors and return new vector
     * @param v1 any vector
     * @param v2 any vector
     * @return new vector after subtraction
     */
    public Vec3f setSub(final Vec3f v1,final Vec3f v2)
    {
        return new Vec3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    /**
     * Scale current vector uniformly and return a new scaled vector
     * @param scale uniform scale factor
     * @return new scaled vector
     */
    public Vec3f scale(final double scale)
    {
        return new Vec3f(this.x * scale, this.y * scale, this.z * scale);
    }

    /**
     * Scale current vector with specific factors for each coordinate and return new scaled vector
     * @param scalex scale factor for x
     * @param scaley scale factor for y
     * @param scalez scale factor for z
     * @return new scaled vector
     */
    public Vec3f scale(final double scalex,final double scaley,final double scalez)
    {
        return new Vec3f(this.x * scalex, this.y * scaley, this.z * scalez);
    }

    /**
     * Scale a given vector by a uniform scale and return new scaled vector
     * @param scale scale factor
     * @param that vector to scale
     * @return new scaled vector
     */
    public Vec3f setScale(final double scale,final Vec3f that)
    {
        return new Vec3f(scale * that.x, scale * that.y, scale * that.z);
    }

    /**
     * Scale a given vector by factors provided in another vector and return new scaled vector
     * @param v1 vector to scale
     * @param v2 scale factors for x, y, and z
     * @return new scaled vector
     */
    public Vec3f setScale(final Vec3f v1,final Vec3f v2)
    {
        return new Vec3f(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z);
    }

    /**
     * Add a given vector that is before-hand scaled, to the current vector and return new vector
     * @param scale scale factor
     * @param that vector to scale and add to current vector
     * @return new vector after addition
     */
    public Vec3f addScale(final double scale,final Vec3f that)
    {
        return new Vec3f(this.x + scale * that.x, this.y + scale * that.y, this.z + scale * that.z);
    }

    /**
     * Multiply a given vector by a matrix and return new vector
     * @param mat any matrix
     * @param v any vector
     * @return new vector after matrix multiplication
     */
    public Vec3f setMatMultiply(final double[] mat,final Vec3f v)
    {
        return new Vec3f(
                mat[0] * v.x + mat[1] * v.y + mat[2] * v.z,
                mat[3] * v.x + mat[4] * v.y + mat[5] * v.z,
                mat[6] * v.x + mat[7] * v.y + mat[8] * v.z
        );
    }

    /**
     * Multiply a given vector by the transpose of a matrix and return new vector
     * @param mat any matrix
     * @param v any vector
     * @return new vector after transpose matrix multiplication
     */
    public Vec3f setTransposeMatMultiply(final double[] mat,final Vec3f v)
    {
        return new Vec3f(
                mat[0] * v.x + mat[3] * v.y + mat[6] * v.z,
                mat[1] * v.x + mat[4] * v.y + mat[7] * v.z,
                mat[2] * v.x + mat[5] * v.y + mat[8] * v.z
        );
    }

    /**
     * Compute dot (inner) product with another vector
     * @param v vector with which dotproduct is computed
     * @return result of dot product
     */
    public double dotProduct(final Vec3f v)
    {
        return this.x*v.x+this.y*v.y+this.z*v.z;
    }

    /**
     * Return the cross product of two vectors
     * @param v1 First vector
     * @param v2 Second vector
     * @return new vector with cross product v1 * v2
     */
    public Vec3f setCrossProduct(final Vec3f v1,final Vec3f v2)
    {
        return new Vec3f(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    /**
     * Return the cross product of this vector with another.
     * @param other The other vector
     * @return A new vector that is the cross product of this and v2
     */
    public Vec3f crossProduct(final Vec3f other)
    {
        return new Vec3f(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }
}
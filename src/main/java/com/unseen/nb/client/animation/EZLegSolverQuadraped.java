package com.unseen.nb.client.animation;

public class EZLegSolverQuadraped extends EZLegSolver{

    public final Leg backLeft, backRight, frontLeft, frontRight;



    public EZLegSolverQuadraped(float forward, float side) {
        this(0, forward, side, side, 1.0F);
    }

    public EZLegSolverQuadraped(float forwardCenter, float forward, float sideBack, float sideFront, float range) {
        super(
                new Leg(forwardCenter - forward, sideBack, range, false),
                new Leg(forwardCenter - forward, -sideBack, range, false),
                new Leg(forwardCenter + forward, sideFront, range, true),
                new Leg(forwardCenter + forward, -sideFront, range, true)
        );
        this.backLeft = this.legs[0];
        this.backRight = this.legs[1];
        this.frontLeft = this.legs[2];
        this.frontRight = this.legs[3];
    }
}

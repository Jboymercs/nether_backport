package com.unseen.nb.client.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.animation.model.BasicModelPart;
import com.unseen.nb.client.animation.model.EZModelAnimator;
import com.unseen.nb.client.animation.util.EZMath;
import com.unseen.nb.common.entity.entities.EntityPiglinZombie;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelPiglinZombie extends BasicModelEntity {
	private BasicModelPart Torso;
	private BasicModelPart ChestArmor;
	private BasicModelPart LegL;
	private BasicModelPart LBoot;
	private BasicModelPart LegR;
	private BasicModelPart RBoot;
	private BasicModelPart RArm;
	private BasicModelPart HoldWeapon;
	private BasicModelPart LArm;
	private BasicModelPart Head;
	private BasicModelPart Snout;
	private BasicModelPart LEar;
	private BasicModelPart cube_r1;
	private BasicModelPart REar;
	private BasicModelPart cube_r2;

	private BasicModelPart cube_r3;

	private BasicModelPart cube_r4;
	//This is your animator where most of the functions for animation are used
	private EZModelAnimator animator;

	public ModelPiglinZombie(float scale) {
		this(scale, 0.0F, 64, 64);
	}

	public ModelPiglinZombie(float scale, float i, int textureWidth, int textureHeight) {

	}

	public void postRenderArm(float scale, EnumHandSide side)
	{
		this.getArmForSide(side).postRender(scale);
	}

	protected ModelRenderer getArmForSide(EnumHandSide side)
	{
		return side == EnumHandSide.LEFT ? this.LArm : this.cube_r3;
	}

	public ModelPiglinZombie() {
		textureWidth = 64;
		textureHeight = 64;

		Torso = new BasicModelPart(this);
		Torso.setRotationPoint(0.0F, 4.0F, 0.0F);
		Torso.cubeList.add(new ModelBox(Torso, 0, 16, -4.0F, -4.0F, -2.0F, 8, 12, 4, 0.0F, false));

		ChestArmor = new BasicModelPart(this);
		ChestArmor.setRotationPoint(0.0F, 0.0F, 0.0F);
		Torso.addChild(ChestArmor);
		ChestArmor.cubeList.add(new ModelBox(ChestArmor, 36, 3, -4.0F, -4.0F, -2.0F, 8, 12, 4, 0.5F, false));

		LegL = new BasicModelPart(this);
		LegL.setRotationPoint(2.0F, 4.0F, 0.0F);
		Torso.addChild(LegL);
		LegL.cubeList.add(new ModelBox(LegL, 0, 32, -2.0F, 4.0F, -2.0F, 4, 12, 4, 0.0F, false));

		LBoot = new BasicModelPart(this);
		LBoot.setRotationPoint(0.0F, 16.0F, 0.0F);
		LegL.addChild(LBoot);
		LBoot.cubeList.add(new ModelBox(LBoot, 0, 48, -2.0F, -12.0F, -2.0F, 4, 6, 4, 0.5F, false));

		LegR = new BasicModelPart(this);
		LegR.setRotationPoint(-2.0F, 4.0F, 0.0F);
		Torso.addChild(LegR);
		LegR.cubeList.add(new ModelBox(LegR, 0, 32, -2.0F, 4.0F, -2.0F, 4, 12, 4, 0.0F, false));

		RBoot = new BasicModelPart(this);
		RBoot.setRotationPoint(0.0F, 16.0F, 0.0F);
		LegR.addChild(RBoot);
		RBoot.cubeList.add(new ModelBox(RBoot, 0, 48, -2.0F, -12.0F, -2.0F, 4, 6, 4, 0.5F, true));

		RArm = new BasicModelPart(this);
		RArm.setRotationPoint(-4.0F, -2.0F, 0.0F);
		Torso.addChild(RArm);


		cube_r3 = new BasicModelPart(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		RArm.addChild(cube_r3);
		setRotationAngle(cube_r3, -1.2217F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 24, 16, -4.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		HoldWeapon = new BasicModelPart(this);
		HoldWeapon.setRotationPoint(-2.0F, 11.0F, 0.0F);
		RArm.addChild(HoldWeapon);
		

		LArm = new BasicModelPart(this);
		LArm.setRotationPoint(4.0F, -2.0F, 0.0F);
		Torso.addChild(LArm);

		cube_r4 = new BasicModelPart(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		LArm.addChild(cube_r4);
		setRotationAngle(cube_r4, -1.2217F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 24, 16, 0.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		Head = new BasicModelPart(this);
		Head.setRotationPoint(0.0F, -6.0F, 0.0F);
		Torso.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -5.0F, -6.0F, -4.0F, 10, 8, 8, 0.0F, false));

		Snout = new BasicModelPart(this);
		Snout.setRotationPoint(0.0F, 0.0F, -4.0F);
		Head.addChild(Snout);
		Snout.cubeList.add(new ModelBox(Snout, 28, 0, -3.0F, 0.0F, -1.0F, 6, 2, 1, 0.0F, false));
		Snout.cubeList.add(new ModelBox(Snout, 28, 3, -2.0F, -2.0F, -1.0F, 4, 2, 1, 0.0F, false));

		LEar = new BasicModelPart(this);
		LEar.setRotationPoint(5.0F, -6.0F, 0.0F);
		Head.addChild(LEar);
		

		cube_r1 = new BasicModelPart(this);
		cube_r1.setRotationPoint(0.0F, 2.0F, 0.0F);
		LEar.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.6981F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 16, 32, -1.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F, true));

		REar = new BasicModelPart(this);
		REar.setRotationPoint(-5.0F, -6.0F, 0.0F);
		Head.addChild(REar);
		

		cube_r2 = new BasicModelPart(this);
		cube_r2.setRotationPoint(0.0F, 2.0F, 0.0F);
		REar.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.6981F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 32, 0.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F, false));

		this.updateDefaultPose();

		this.animator = EZModelAnimator.create();
	}

	@Override
	public void animate(IAnimatedEntity entity) {
		//Always include this in the beginning of this method
		animator.update(entity);
		//Always include this in the beginning of this method
		animator.setAnimation(EntityPiglinZombie.ANIMATION_ATTACK_MELEE);
		//
		animator.startKeyframe(8);
		animator.rotate(cube_r3, (float) Math.toRadians(-50), (float) Math.toRadians(15), 0);
		animator.rotate(LArm, (float) Math.toRadians(-50), (float) Math.toRadians(-15),0);
		animator.endKeyframe();
		//
		animator.setStaticKeyframe(7);
		//
		animator.startKeyframe(5);
		animator.rotate(cube_r3, (float) Math.toRadians(20), (float) Math.toRadians(-20),0 );
		animator.rotate(LArm, (float) Math.toRadians(20), (float) Math.toRadians(20), 0);
		animator.endKeyframe();
		//
		animator.resetKeyframe(5);
	}

	@Override
	public Iterable<BasicModelPart> getAllParts() {
		return ImmutableList.of(Torso,ChestArmor,LegL,LBoot,LegR,RBoot,RArm,HoldWeapon,LArm,Head,Snout,LEar,cube_r1,REar,cube_r2, cube_r3, cube_r4);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		EntityPiglinZombie pigling = ((EntityPiglinZombie) entityIn);

		float walkSpeed = 0.5F;
		float walkDegree = 1F;

		//Arms
		if(!pigling.isMeleeAttack()) {
			this.cube_r3.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.cube_r4.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.cube_r3.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.cube_r4.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}

		//Body Bobbing
		float bodyBob = EZMath.walkValue(limbSwing, limbSwingAmount, walkSpeed * 1.2F, 0.5F, 1F, true);
		this.Torso.rotationPointY += bodyBob;
		//Legs Walking
		this.walk(LegR, walkSpeed, walkDegree, true, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.walk(LegL, walkSpeed, walkDegree, false, 0F, 0.1F, limbSwing, limbSwingAmount);
		//Ear Movements
		this.flap(LEar, walkSpeed, walkDegree * 0.25F, true, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.flap(REar, walkSpeed, walkDegree * 0.25F, false, 0F, 0.1F, limbSwing, limbSwingAmount);
		//Again this is for Individual components such as heads to look as they please
		this.faceTarget(netHeadYaw, headPitch, 1, Head);
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Torso.render(f5);
	}

	public void setRotationAngle(BasicModelPart modelPart, float x, float y, float z) {
		modelPart.rotateAngleX = x;
		modelPart.rotateAngleY = y;
		modelPart.rotateAngleZ = z;
	}
}
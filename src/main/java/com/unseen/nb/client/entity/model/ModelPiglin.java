package com.unseen.nb.client.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.animation.model.BasicModelPart;
import com.unseen.nb.client.animation.model.EZModelAnimator;
import com.unseen.nb.client.animation.util.EZMath;
import com.unseen.nb.common.entity.entities.EntityPiglin;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelPiglin extends BasicModelEntity {
	public BasicModelPart Torso;
	public BasicModelPart LegL;
	public BasicModelPart LegR;
	public BasicModelPart RArm;
	public BasicModelPart HoldWeapon;

	public BasicModelPart HoldWeaponL;
	public BasicModelPart LArm;
	public BasicModelPart Head;
	private BasicModelPart Snout;
	private BasicModelPart LEar;
	private BasicModelPart cube_r1;
	private BasicModelPart REar;
	private BasicModelPart cube_r2;

	//Armor
	public BasicModelPart ChestArmor;
	public BasicModelPart RShoulderPad;
	public BasicModelPart LShoulderPad;
	public BasicModelPart Helmet;
	public BasicModelPart LBoot;
	public BasicModelPart RBoot;

	//This is your animator where most of the functions for animation are used
	private EZModelAnimator animator;

	public ModelPiglin(float scale) {
		this(scale, 0.0F, 64, 64);
	}

	public ModelPiglin(float scale, float i, int textureWidth, int textureHeight) {

	}

	public void postRenderArm(float scale, EnumHandSide side)
	{
		this.getArmForSide(side).postRender(scale);
	}

	protected ModelRenderer getArmForSide(EnumHandSide side)
	{
		return side == EnumHandSide.LEFT ? this.LArm : this.RArm;
	}


	public ModelPiglin() {
		textureWidth = 64;
		textureHeight = 64;


		Torso = new BasicModelPart(this);
		Torso.setRotationPoint(0.0F, 4.0F, 0.0F);
		Torso.cubeList.add(new ModelBox(Torso, 0, 16, -4.0F, -4.0F, -2.0F, 8, 12, 4, 0.0F, false));

		LegL = new BasicModelPart(this);
		LegL.setRotationPoint(2.0F, 4.0F, 0.0F);
		Torso.addChild(LegL);
		LegL.cubeList.add(new ModelBox(LegL, 0, 32, -2.0F, 4.0F, -2.0F, 4, 12, 4, 0.0F, false));

		LegR = new BasicModelPart(this);
		LegR.setRotationPoint(-2.0F, 4.0F, 0.0F);
		Torso.addChild(LegR);
		LegR.cubeList.add(new ModelBox(LegR, 0, 32, -2.0F, 4.0F, -2.0F, 4, 12, 4, 0.0F, false));

		RArm = new BasicModelPart(this);
		RArm.setRotationPoint(-4.0F, -2.0F, 0.0F);
		Torso.addChild(RArm);
		RArm.cubeList.add(new ModelBox(RArm, 24, 16, -4.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		HoldWeapon = new BasicModelPart(this);
		HoldWeapon.setRotationPoint(-2.0F, 9.0F, 0.0F);
		RArm.addChild(HoldWeapon);


		LArm = new BasicModelPart(this);
		LArm.setRotationPoint(4.0F, -2.0F, 0.0F);
		Torso.addChild(LArm);
		LArm.cubeList.add(new ModelBox(LArm, 24, 16, 0.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true));

		HoldWeaponL = new BasicModelPart(this);
		HoldWeaponL.setRotationPoint(2.0F, 11.0F, 0.0F);
		LArm.addChild(HoldWeaponL);

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

		Helmet = new BasicModelPart(this);
			Helmet.setRotationPoint(0.0F, 2.0F, 0.0F);
			Head.addChild(Helmet);
			Helmet.cubeList.add(new ModelBox(Helmet, 32, 48, -4.0F, -8.0F, -4.0F, 8, 8, 8, 1.1F, false));


		ChestArmor = new BasicModelPart(this);
			ChestArmor.setRotationPoint(0.0F, 0.0F, 0.0F);
			Torso.addChild(ChestArmor);
			ChestArmor.cubeList.add(new ModelBox(ChestArmor, 36, 3, -4.0F, -4.0F, -2.0F, 8, 12, 4, 0.5F, false));


		RShoulderPad = new BasicModelPart(this);
		RShoulderPad.setRotationPoint(0.0F, 0.0F, 0.0F);
		RArm.addChild(RShoulderPad);
		RShoulderPad.cubeList.add(new ModelBox(RShoulderPad, 40, 20, -4.0F, -2.0F, -2.0F, 4, 12, 4, 0.5F, false));

		LShoulderPad = new BasicModelPart(this);
		LShoulderPad.setRotationPoint(0.0F, 0.0F, 0.0F);
		LArm.addChild(LShoulderPad);
		LShoulderPad.cubeList.add(new ModelBox(LShoulderPad, 40, 20, 0.0F, -2.0F, -2.0F, 4, 12, 4, 0.5F, true));


		RBoot = new BasicModelPart(this);
			RBoot.setRotationPoint(0.0F, 16.0F, 0.0F);
			LegR.addChild(RBoot);
			RBoot.cubeList.add(new ModelBox(RBoot, 0, 48, -2.0F, -6.0F, -2.0F, 4, 6, 4, 0.5F, false));

			LBoot = new BasicModelPart(this);
			LBoot.setRotationPoint(0.0F, 16.0F, 0.0F);
			LegL.addChild(LBoot);
			LBoot.cubeList.add(new ModelBox(LBoot, 0, 48, -2.0F, -6.0F, -2.0F, 4, 6, 4, 0.5F, true));


				RBoot.isHidden = true;
				LBoot.isHidden = true;
			RShoulderPad.isHidden = true;
			LShoulderPad.isHidden = true;
			ChestArmor.isHidden = true;
			Helmet.isHidden =true;



		this.updateDefaultPose();

		this.animator = EZModelAnimator.create();
	}

	@Override
	public Iterable<BasicModelPart> getAllParts() {
		return ImmutableList.of(Torso,LegL,LegR,RArm,HoldWeapon,LArm,Head,Snout,LEar,cube_r1,REar,cube_r2, RBoot, LBoot, LShoulderPad, RShoulderPad, ChestArmor, Helmet, HoldWeaponL);
	}

	@Override
	public void animate(IAnimatedEntity entity) {
		//Always include this in the beginning of this method
		animator.update(entity);
		//
		animator.setAnimation(EntityPiglin.ANIMATION_SHORT_TRADE);
		//
		animator.startKeyframe(5);
		animator.rotate(Head, (float) Math.toRadians(20), (float) Math.toRadians(-10), 0);
		animator.rotate(RArm, 0,0,0);
		animator.rotate(LArm, (float) Math.toRadians(-50), (float) Math.toRadians(20), 0);
		animator.endKeyframe();
		//
		animator.setStaticKeyframe(105);
		//
		animator.resetKeyframe(10);
		//
		animator.setAnimation(EntityPiglin.ANIMATION_ATTACK_MELEE);
		//
		animator.startKeyframe(4);
		animator.rotate(RArm, (float) Math.toRadians(-55),0, (float) Math.toRadians(-7));
		animator.rotate(LArm, (float) Math.toRadians(10), 0, (float) Math.toRadians(-5));
		animator.endKeyframe();
		//
		animator.startKeyframe(4);
		animator.rotate(RArm, (float) Math.toRadians(-110),0, (float) Math.toRadians(-15));
		animator.rotate(LArm, (float) Math.toRadians(20), 0, (float) Math.toRadians(-10));
		animator.endKeyframe();
		//
		animator.setStaticKeyframe(7);
		//
		animator.startKeyframe(5);
		animator.rotate(RArm, (float) Math.toRadians(20), (float) Math.toRadians(-20), (float) Math.toRadians(10));
		animator.rotate(LArm, (float) Math.toRadians(-10), 0, (float) Math.toRadians(-10));
		animator.endKeyframe();
		//
		animator.resetKeyframe(5);
		//
		animator.setAnimation(EntityPiglin.ANIMATION_ATTACK_RANGED);
		//
		animator.startKeyframe(5);
		animator.rotate(RArm, (float) Math.toRadians(-40), (float) Math.toRadians(-40), 0);
		animator.rotate(LArm, (float) Math.toRadians(-60), (float) Math.toRadians(30), 0);
		animator.endKeyframe();
		//
		animator.startKeyframe(10);
		animator.rotate(RArm, (float) Math.toRadians(-30), (float) Math.toRadians(-30), 0);
		animator.rotate(LArm, (float) Math.toRadians(-70), (float) Math.toRadians(40), 0);
		animator.endKeyframe();
		//
		animator.startKeyframe(10);
		animator.rotate(LArm, (float) Math.toRadians(-80),(float) Math.toRadians(30),0);
		animator.rotate(RArm, (float) Math.toRadians(-80), (float) Math.toRadians(-20), 0);
		animator.endKeyframe();

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		EntityPiglin pigling = ((EntityPiglin) entityIn);

		float walkSpeed = 0.5F;
		float walkDegree = 1F;
		float f = 1.0F;

		//Arm Movements
		if(pigling.isLoadedACrossBow()) {
			//animator.rotate(LArm, (float) Math.toRadians(-80),(float) Math.toRadians(30),0);
			//animator.rotate(RArm, (float) Math.toRadians(-80), (float) Math.toRadians(-20), 0);
			RArm.rotateAngleX = (-(float)Math.PI / 2F) + Head.rotateAngleX + 0.1F;
			RArm.rotateAngleY = -0.3F + Head.rotateAngleY;
			LArm.rotateAngleX = -1.5F + Head.rotateAngleX;
			LArm.rotateAngleY = 0.6F + Head.rotateAngleY;

		}else if(!pigling.isMeleeAttack() && !pigling.isRangedAttack() && !pigling.isLoadedACrossBow()) {
			this.walk(RArm, walkSpeed, walkDegree, true, 0F, 0.1F, limbSwing, limbSwingAmount);
			this.walk(LArm, walkSpeed, walkDegree, false, 0F, 0.1F, limbSwing, limbSwingAmount);
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

	public void setRotationAngle(BasicModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
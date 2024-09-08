package com.unseen.nb.client.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.unseen.nb.client.animation.IAnimatedEntity;
import com.unseen.nb.client.animation.model.BasicModelEntity;
import com.unseen.nb.client.animation.model.BasicModelPart;
import com.unseen.nb.client.animation.model.EZModelAnimator;
import com.unseen.nb.client.animation.util.EZMath;
import com.unseen.nb.common.entity.entities.EntityHoglin;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelHoglin extends BasicModelEntity {
	private final BasicModelPart Torso;
	private final BasicModelPart BLeftLeg;
	private final BasicModelPart BRightLeg;
	private final BasicModelPart FLeftLeg;
	private final BasicModelPart FRightLeg;
	private final BasicModelPart Head;
	private final BasicModelPart REar;
	private final BasicModelPart REarCube_r1;
	private final BasicModelPart LEar;
	private final BasicModelPart LEarCube_r1;

	//This is your animator where most of the functions for animation are used
	private EZModelAnimator animator;

	public ModelHoglin() {
		textureWidth = 128;
		textureHeight = 128;

		Torso = new BasicModelPart(this);
		Torso.setRotationPoint(0.0F, 13.0F, 0.0F);
		Torso.cubeList.add(new ModelBox(Torso, 0, 0, -8.0F, -14.0F, -13.0F, 16, 14, 26, 0.0F, false));
		Torso.cubeList.add(new ModelBox(Torso, 0, 46, 0.0F, -21.0F, -16.0F, 0, 10, 19, 0.0F, false));

		BLeftLeg = new BasicModelPart(this);
		BLeftLeg.setRotationPoint(5.5F, 0.0F, 9.5F);
		Torso.addChild(BLeftLeg);
		BLeftLeg.cubeList.add(new ModelBox(BLeftLeg, 61, 60, -2.5F, 0.0F, -2.5F, 5, 11, 5, 0.0F, false));

		BRightLeg = new BasicModelPart(this);
		BRightLeg.setRotationPoint(-5.5F, 0.0F, 9.5F);
		Torso.addChild(BRightLeg);
		BRightLeg.cubeList.add(new ModelBox(BRightLeg, 58, 0, -2.5F, 0.0F, -2.5F, 5, 11, 5, 0.0F, false));

		FLeftLeg = new BasicModelPart(this);
		FLeftLeg.setRotationPoint(5.5F, 0.0F, -9.5F);
		Torso.addChild(FLeftLeg);
		FLeftLeg.cubeList.add(new ModelBox(FLeftLeg, 47, 40, -3.5F, 0.0F, -2.5F, 6, 11, 6, 0.0F, false));

		FRightLeg = new BasicModelPart(this);
		FRightLeg.setRotationPoint(-5.5F, 0.0F, -9.5F);
		Torso.addChild(FRightLeg);
		FRightLeg.cubeList.add(new ModelBox(FRightLeg, 0, 0, -2.5F, 0.0F, -2.5F, 6, 11, 6, 0.0F, false));

		Head = new BasicModelPart(this);
		Head.setRotationPoint(0.0F, -13.0F, -12.0F);
		Torso.addChild(Head);
		setRotationAngle(Head, 0.7854F, 0.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 40, -7.0F, -2.0F, -20.0F, 14, 6, 19, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 40, 6.0F, -8.0F, -15.0F, 2, 11, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 40, -8.0F, -8.0F, -15.0F, 2, 11, 2, 0.0F, false));

		REar = new BasicModelPart(this);
		REar.setRotationPoint(-7.0F, -2.0F, -4.0F);
		Head.addChild(REar);
		

		REarCube_r1 = new BasicModelPart(this);
		REarCube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		REar.addChild(REarCube_r1);
		setRotationAngle(REarCube_r1, 0.0F, 0.0F, -0.6109F);
		REarCube_r1.cubeList.add(new ModelBox(REarCube_r1, 58, 16, -6.0F, 0.0F, -2.0F, 6, 1, 4, 0.0F, false));

		LEar = new BasicModelPart(this);
		LEar.setRotationPoint(7.0F, -2.0F, -4.0F);
		Head.addChild(LEar);
		

		LEarCube_r1 = new BasicModelPart(this);
		LEarCube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		LEar.addChild(LEarCube_r1);
		setRotationAngle(LEarCube_r1, 0.0F, 0.0F, 0.6109F);
		LEarCube_r1.cubeList.add(new ModelBox(LEarCube_r1, 0, 17, 0.0F, 0.0F, -2.0F, 6, 1, 4, 0.0F, false));

		this.updateDefaultPose();

		this.animator = EZModelAnimator.create();
	}

	@Override
	public Iterable<BasicModelPart> getAllParts() {
		return ImmutableList.of(Torso, BLeftLeg, BRightLeg, FLeftLeg, FRightLeg, Head, REar, REarCube_r1, LEar, LEarCube_r1);
	}

	@Override
	public void animate(IAnimatedEntity entity) {
		//Always include this in the beginning of this method
		animator.update(entity);
		//
		animator.setAnimation(EntityHoglin.ANIMATION_ATTACK_MELEE);
		//
		animator.startKeyframe(5);
		animator.rotate(Head, (float) Math.toRadians(35), 0, 0);
		animator.rotate(Torso, (float) Math.toRadians(2.5), 0, 0);
		animator.endKeyframe();
		//
		animator.setStaticKeyframe(5);
		//
		animator.startKeyframe(5);
		animator.rotate(Head, (float) Math.toRadians(-40), 0, 0);
		animator.rotate(Torso, (float) Math.toRadians(5), 0, 0);
		animator.endKeyframe();
		//
		animator.resetKeyframe(5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		EntityHoglin hoglin = ((EntityHoglin) entityIn);

		float walkSpeed = 0.5F;
		float walkDegree = 1F;
		float f = 1.0F;


		//Body Bobbing
		float bodyBob = EZMath.walkValue(limbSwing, limbSwingAmount, walkSpeed * 1.5F, 0.5F, 1F, true);
		this.Torso.rotationPointY += bodyBob;
		//Legs Walking
		this.walk(FLeftLeg, walkSpeed, walkDegree, true, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.walk(FRightLeg, walkSpeed, walkDegree, false, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.walk(BLeftLeg, walkSpeed, walkDegree, false, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.walk(BRightLeg, walkSpeed, walkDegree, true, 0F, 0.1F, limbSwing, limbSwingAmount);
		//Ear Movements
		this.flap(LEar, walkSpeed, walkDegree * 0.25F, true, 0F, 0.1F, limbSwing, limbSwingAmount);
		this.flap(REar, walkSpeed, walkDegree * 0.25F, false, 0F, 0.1F, limbSwing, limbSwingAmount);
		//Again this is for Individual components such as heads to look as they please
		this.faceTarget(netHeadYaw, headPitch, 1, Head);
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		if(this.isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0.85F, 0);
			GlStateManager.scale(0.4F, 0.4F, 0.4F);
			Torso.render(f5);
			GlStateManager.popMatrix();
		} else {
			Torso.render(f5);
		}

	}

	public void setRotationAngle(BasicModelPart modelPart, float x, float y, float z) {
		modelPart.rotateAngleX = x;
		modelPart.rotateAngleY = y;
		modelPart.rotateAngleZ = z;
	}
}
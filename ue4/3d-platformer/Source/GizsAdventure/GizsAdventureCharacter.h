// Copyright 1998-2017 Epic Games, Inc. All Rights Reserved.
#pragma once
#include "GameFramework/Character.h"
#include "GizsAdventureCharacter.generated.h"

UCLASS(config=Game)
class AGizsAdventureCharacter : public ACharacter
{
	GENERATED_BODY()

	/** Camera boom positioning the camera behind the character */
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category = Camera, meta = (AllowPrivateAccess = "true"))
	class USpringArmComponent* CameraBoom;

	/** Follow camera */
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category = Camera, meta = (AllowPrivateAccess = "true"))
	class UCameraComponent* FollowCamera;
private:
	float DefaultMaxWalkingSpeed = 0.0f;

	bool bPressedSprint = false;
	float SprintModifier = 2.0f;

	float StartedFallingZPosition = 0.0f;
	float LandedZPosition = 0.0f;

	float FallDamageAmount = 0.25f;
	bool bFallDamageEligible = false;

public:
	AGizsAdventureCharacter();

	/** Base turn rate, in deg/sec. Other scaling may affect final turn rate. */
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category=Camera)
	float BaseTurnRate;

	/** Base look up/down rate, in deg/sec. Other scaling may affect final rate. */
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category=Camera)
	float BaseLookUpRate;

	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category = Player)
	float Health;

	UPROPERTY(VisibleAnywhere, BluePrintReadWrite, Category = Player)
	bool bCanDash;

	UFUNCTION(BlueprintImplementableEvent, Category = Player)
	void PlayerDied();

protected:

	/** Resets HMD orientation in VR. */
	void OnResetVR();

	/** Called for forwards/backward input */
	void MoveForward(float Value);

	/** Called for side to side input */
	void MoveRight(float Value);

	/** 
	 * Called via input to turn at a given rate. 
	 * @param Rate	This is a normalized rate, i.e. 1.0 means 100% of desired turn rate
	 */
	void TurnAtRate(float Rate);

	/**
	 * Called via input to turn look up/down at a given rate. 
	 * @param Rate	This is a normalized rate, i.e. 1.0 means 100% of desired turn rate
	 */
	void LookUpAtRate(float Rate);

	/** Handler for when a touch input begins. */
	void TouchStarted(ETouchIndex::Type FingerIndex, FVector Location);

	/** Handler for when a touch input stops. */
	void TouchStopped(ETouchIndex::Type FingerIndex, FVector Location);

	void OnStartSprint();
	void OnStopSprint();

	virtual void Landed(const FHitResult & Hit) override;
	virtual void Falling() override;

	bool ShouldTakeFallDamage(float StartingZPosition, float EndZPosition);
	void TakeFallDamage();

	void PostDamageDeathCheck();

protected:
	// APawn interface
	virtual void SetupPlayerInputComponent(class UInputComponent* PlayerInputComponent) override;
	// End of APawn interface

public:
	/** Returns CameraBoom subobject **/
	FORCEINLINE class USpringArmComponent* GetCameraBoom() const { return CameraBoom; }
	/** Returns FollowCamera subobject **/
	FORCEINLINE class UCameraComponent* GetFollowCamera() const { return FollowCamera; }
};


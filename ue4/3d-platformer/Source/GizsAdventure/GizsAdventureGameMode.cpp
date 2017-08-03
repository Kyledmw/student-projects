// Copyright 1998-2017 Epic Games, Inc. All Rights Reserved.

#include "GizsAdventure.h"
#include "GizsAdventureGameMode.h"
#include "GizsAdventureCharacter.h"

AGizsAdventureGameMode::AGizsAdventureGameMode()
{
	// set default pawn class to our Blueprinted character
	static ConstructorHelpers::FClassFinder<APawn> PlayerPawnBPClass(TEXT("/Game/Characters/Player/Blueprints/Character_Player"));
	if (PlayerPawnBPClass.Class != NULL)
	{
		DefaultPawnClass = PlayerPawnBPClass.Class;
	}
}

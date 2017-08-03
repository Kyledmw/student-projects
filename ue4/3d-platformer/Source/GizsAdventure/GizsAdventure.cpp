// Copyright 1998-2017 Epic Games, Inc. All Rights Reserved.

#include "GizsAdventure.h"


IMPLEMENT_PRIMARY_GAME_MODULE( FDefaultGameModuleImpl, GizsAdventure, "GizsAdventure" );

//General Log
DEFINE_LOG_CATEGORY(General);

//Logging during game startup
DEFINE_LOG_CATEGORY(Startup);

//Logging for your AI system
DEFINE_LOG_CATEGORY(AI);

//Logging for Critical Errors that must always be addressed
DEFINE_LOG_CATEGORY(CriticalErrors);
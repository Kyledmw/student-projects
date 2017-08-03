;
;       Author : Kyle Williamson
;
;       File   : PROJECT.ASM
;       
;       This program is a graphic scene of a city centre building scene.
;		The user can interact by clicking on one of the buttons on the screen.
;
TITLE   PROJECT

SSEG    SEGMENT PARA STACK 'STACK'         ;Stack segment
        DB      64 DUP('STACK   ')
SSEG    ENDS


DSEG    SEGMENT PARA PUBLIC 'DATA'         ;Code segment
HEIGHTS1		DW	50, 140
LENGTHS1		DW	40, 40
WIDTHS1			DW	40, 40


HEIGHTS2		DW	50, 100, 140, 50, 120
LENGTHS2		DW	50, 50, 50, 50, 50
WIDTHS2			DW	60, 60, 60, 60, 60


HEIGHTS3		DW	20, 80, 220, 100, 150, 100, 40
LENGTHS3		DW	40, 50, 50, 50, 50, 50, 20
WIDTHS3			DW	60, 60, 60, 60, 60, 60, 60


HEIGHTS4		DW	80, 60, 150, 110, 80
LENGTHS4		DW	20, 50, 50, 50, 50
WIDTHS4			DW	60, 60, 60, 60, 60

STRDRAWNORM		DB	'Draw normal'
STRDRAWNORM_SZ	EQU	$ - STRDRAWNORM

STRDRAWANIM		DB	'Draw animatable'
STRDRAWANIM_SZ	EQU	$ - STRDRAWANIM

STRANIMATEBLD		DB	'Animate Car'
STRANIMATEBLD_SZ	EQU	$ - STRANIMATEBLD

DSEG    ENDS


CSEG    SEGMENT PARA PUBLIC 'CODE'
        ASSUME CS:CSEG, DS:DSEG, SS:SSEG

MAIN    PROC    FAR

        PUSH    DS                         ;These 5 lines are
        MOV     AX,0                       ;required for all .ASM
        PUSH    AX                         ;programs.
        MOV     AX,DSEG                    ;
        MOV     DS,AX                      ;

		;The program begins here
		
		CALL SETUPVIEW
		
		CALL DRAWEXITBUTTON
		
		CALL DRAWBUTTONS
		CALL SETBUTTONTEXT
		
		MOV AX, 01H;
		INT 33H
AGAIN:
		MOV BX, 00H
		MOV AX, 05H
		INT 33H
		CMP BX, 1
		JNE AGAIN
		CALL BUTTON1CLICKED
		CALL BUTTON2CLICKED
		CALL BUTTON3CLICKED
		CALL EXITBUTTONCLICKED
		CMP BX, 1
		JE FINISH
		JMP AGAIN
FINISH:

		MOV AL, 2
		MOV AH, 00H
		INT 10H
		
        RET  

MAIN    ENDP

;-------------------------------------------------------------------------------------------------------
; Name: WRITEPIXEL
;
; Description: Writes a pixel to the screen with the given parameters
;
; Input Params:	DX = Row/Line
;				CX = Column
;				AL = Colour
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
WRITEPIXEL	PROC
		PUSH AX
		PUSH BX
		
		MOV AH, 0CH
		MOV BH, 00H
		INT 10H
		
		POP BX
		POP AX	
		RET
WRITEPIXEL ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW0DEGLINE
;
; Description: Draws a horizontal line of pixels with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW0DEGLINE PROC
		PUSH CX
		PUSH SI	
AGAIN0:
		CMP SI, 0
		JE FINISH0
		DEC SI
		CALL WRITEPIXEL
		INC CX
		JMP AGAIN0
FINISH0:
		POP SI
		POP CX
		RET
DRAW0DEGLINE ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW45DEGLINE
;
; Description: Draws a 45 degree line with given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW45DEGLINE	PROC
		PUSH DX
		PUSH CX 
		PUSH SI
AGAIN45:
		CMP SI, 0
		JE	FINISH45
		DEC	SI
		CALL WRITEPIXEL
		DEC DX
		INC CX
		JMP AGAIN45
FINISH45:
		POP SI
		POP CX
		POP DX
		RET
DRAW45DEGLINE	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW90DEGLINE
;
; Description: Draws a vertical line of pixels with the given parameters at 90 degrees
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW90DEGLINE	PROC
		PUSH DX
		PUSH DI
AGAIN90:
		CMP DI, 0
		JE FINISH90
		DEC DI
		CALL WRITEPIXEL
		DEC DX
		JMP AGAIN90
FINISH90:
		POP DI
		POP DX
		RET
DRAW90DEGLINE	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW135DEGLINE
;
; Description: Draw a 135 degree line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW135DEGLINE	PROC
		PUSH DX
		PUSH CX 
		PUSH SI
AGAIN135:
		CMP SI, 0
		JE	FINISH135
		DEC	SI
		CALL WRITEPIXEL
		DEC DX
		DEC CX
		JMP AGAIN135
FINISH135:
		POP SI
		POP CX
		POP DX
		RET
DRAW135DEGLINE ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW180DEGLINE
;
; Description: Draws a horizontal line of pixels with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW180DEGLINE PROC
		PUSH CX
		PUSH SI	
AGAIN180:
		CMP SI, 0
		JE FINISH180
		DEC SI
		CALL WRITEPIXEL
		DEC CX
		JMP AGAIN180
FINISH180:
		POP SI
		POP CX
		RET
DRAW180DEGLINE ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW225DEGLINE
;
; Description: Draws a 45 degree line down with given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = LENGTH
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW225DEGLINE PROC
		PUSH DX
		PUSH CX 
		PUSH SI
AGAIN225:
		CMP SI, 0
		JE	FINISH225
		DEC	SI
		CALL WRITEPIXEL
		INC DX
		DEC CX
		JMP AGAIN225
FINISH225:
		POP SI
		POP CX
		POP DX
		RET
DRAW225DEGLINE ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW270DEGLINE
;
; Description: Draws a vertical line of pixels with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW270DEGLINE	PROC
		PUSH DX
		PUSH DI
AGAIN270:
		CMP DI, 0
		JE FINISH270
		DEC DI
		INC DX
		CALL WRITEPIXEL
		JMP AGAIN270
FINISH270:
		POP DI
		POP DX
		RET
DRAW270DEGLINE ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW315DEGLINE
;
; Description: Draws a 315 degree line down with given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW315DEGLINE	PROC
		PUSH DX
		PUSH CX 
		PUSH SI
AGAIN315:
		CMP SI, 0
		JE	FINISH315
		DEC	SI
		CALL WRITEPIXEL
		INC DX
		INC CX
		JMP AGAIN315
FINISH315:
		POP SI
		POP CX
		POP DX
		RET
DRAW315DEGLINE	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW90DEG45DEGTRIANGLELEFT
;
; Description: Draws a 90 45 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW90DEG45DEGTRIANGLELEFT	PROC
		PUSH SI
		PUSH DX
		MOV SI, DI
		CALL DRAW90DEGLINE
		CALL DRAW180DEGLINE
		SUB DX, DI
		CALL DRAW225DEGLINE
		POP DX
		POP SI
		RET
DRAW90DEG45DEGTRIANGLELEFT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILL90DEG45DEGTRIANGLELEFT
;
; Description: Fills a 90 45 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILL90DEG45DEGTRIANGLELEFT	PROC
		PUSH DI
		PUSH CX
AGAINFILL90DEG45DEGTRIANGLELEFT:
		CMP DI, 0
		JE FINISHFILL90DEG45DEGTRIANGLELEFT
		CALL DRAW90DEGLINE
		DEC DI
		DEC CX
		JMP AGAINFILL90DEG45DEGTRIANGLELEFT
FINISHFILL90DEG45DEGTRIANGLELEFT:
		POP CX
		POP DI
		RET
FILL90DEG45DEGTRIANGLELEFT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW90DEG45DEGTRIANGLERIGHT
;
; Description: Draws a 90 45 degree triangle on the right side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW90DEG45DEGTRIANGLERIGHT	PROC
		PUSH SI
		PUSH DX
		MOV SI, DI
		CALL DRAW90DEGLINE
		CALL DRAW0DEGLINE
		SUB DX, DI
		CALL DRAW315DEGLINE
		POP DX
		POP SI
		RET
DRAW90DEG45DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILL90DEG45DEGTRIANGLERIGHT
;
; Description: Fills a 90 45 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILL90DEG45DEGTRIANGLERIGHT	PROC
		PUSH DI
		PUSH CX
AGAINFILL90DEG45DEGTRIANGLERIGHT:
		CMP DI, 0
		JE FINISHFILL90DEG45DEGTRIANGLERIGHT
		CALL DRAW90DEGLINE
		DEC DI
		INC CX
		JMP AGAINFILL90DEG45DEGTRIANGLERIGHT
FINISHFILL90DEG45DEGTRIANGLERIGHT:
		POP CX
		POP DI
		RET
FILL90DEG45DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW90DEG225DEGTRIANGLELEFT
;
; Description: Draws a 90 225 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW90DEG225DEGTRIANGLELEFT	PROC
		PUSH SI
		PUSH DX
		MOV SI, DI
		CALL DRAW270DEGLINE
		CALL DRAW180DEGLINE
		ADD DX, DI
		CALL DRAW135DEGLINE
		POP DX
		POP SI
		RET
DRAW90DEG225DEGTRIANGLELEFT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILL90DEG225DEGTRIANGLELEFT
;
; Description: Fills a 90 225 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILL90DEG225DEGTRIANGLELEFT	PROC
		PUSH DI
		PUSH CX
AGAINFILL90DEG225DEGTRIANGLELEFT:
		CMP DI, 0
		JE FINISHFILL90DEG225DEGTRIANGLELEFT
		CALL DRAW270DEGLINE
		DEC DI
		DEC CX
		JMP AGAINFILL90DEG225DEGTRIANGLELEFT
FINISHFILL90DEG225DEGTRIANGLELEFT:
		POP CX
		POP DI
		RET
FILL90DEG225DEGTRIANGLELEFT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW90DEG225DEGTRIANGLERIGHT
;
; Description: Draws a 90 225 degree triangle on the right side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW90DEG225DEGTRIANGLERIGHT	PROC
		PUSH SI
		PUSH DX
		MOV SI, DI
		CALL DRAW270DEGLINE
		CALL DRAW0DEGLINE
		ADD DX, DI
		CALL DRAW45DEGLINE
		POP DX
		POP SI
		RET
DRAW90DEG225DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILL90DEG225DEGTRIANGLERIGHT
;
; Description: Fills a 90 225 degree triangle on the left side of the vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILL90DEG225DEGTRIANGLERIGHT	PROC
		PUSH DI
		PUSH CX
AGAINFILL90DEG225DEGTRIANGLERIGHT:
		CMP DI, 0
		JE FINISHFILL90DEG225DEGTRIANGLERIGHT
		CALL DRAW270DEGLINE
		DEC DI
		INC CX
		JMP AGAINFILL90DEG225DEGTRIANGLERIGHT
FINISHFILL90DEG225DEGTRIANGLERIGHT:
		POP CX
		POP DI
		RET
FILL90DEG225DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAW315DEG45DEGTRIANGLERIGHT
;
; Description: Draws a 315 45 degree triangle to the right of a vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAW315DEG45DEGTRIANGLERIGHT	PROC
		PUSH SI
		PUSH DX
		MOV SI, DI
		SHR SI, 1
		ADD SI, 1
		CALL DRAW315DEGLINE
		ADD DX, DI
		CALL DRAW45DEGLINE
		POP DX
		POP SI
		RET
DRAW315DEG45DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILL315DEG45DEGTRIANGLERIGHT
;
; Description: Fills a 315 45 degree triangle to the right of a vertical line
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				DI = Height/Length
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILL315DEG45DEGTRIANGLERIGHT	PROC
		PUSH DX
		PUSH CX
		PUSH DI
		PUSH SI
		MOV SI, DI
		SHR SI, 1
		ADD SI, 1
		ADD DX, DI
AGAINFILL315DEG45DEGTRIANGLERIGHT:
		CMP SI, 0
		JE FINISHFILL315DEG45DEGTRIANGLERIGHT
		DEC SI
		CALL DRAW90DEGLINE
		DEC DX
		INC CX
		SUB DI, 2
		JMP AGAINFILL315DEG45DEGTRIANGLERIGHT
FINISHFILL315DEG45DEGTRIANGLERIGHT:
		POP SI
		POP DI
		POP CX
		POP DX
		RET
FILL315DEG45DEGTRIANGLERIGHT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWBOXVERTICALHORIZONTAL
;
; Description: Draws a box with given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Width
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBOXVERTICALHORIZONTAL PROC
		CALL DRAW270DEGLINE
		CALL DRAW0DEGLINE

		PUSH CX
		
		ADD CX, SI
		CALL DRAW270DEGLINE
		
		POP CX
		PUSH DX
		
		ADD DX, DI
		CALL DRAW0DEGLINE
		
		POP DX
		
		RET
DRAWBOXVERTICALHORIZONTAL ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOXVERTICALHORIZONTAL
;
; Description: Fills a box with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Width
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOXVERTICALHORIZONTAL PROC
		PUSH SI
		PUSH DI
		PUSH DX
		INC DX
		INC CX
		DEC SI
		DEC DI
AGAINFILLBOXVERTICALHORIZONTAL:		
		CMP DI, 0
		JE FINISHFILLBOXVERTICALHORIZONTAL
		CALL DRAW0DEGLINE
		DEC DI
		INC DX
		JMP AGAINFILLBOXVERTICALHORIZONTAL
FINISHFILLBOXVERTICALHORIZONTAL:		
		POP DX
		POP DI
		POP SI
		RET
FILLBOXVERTICALHORIZONTAL ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWBOXVERTICAL225DEG
;
; Description: Draw a box with vertical and 45 degree lines
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBOXVERTICAL225DEG PROC
		PUSH DX
		PUSH CX
		
		CALL DRAW90DEGLINE
		CALL DRAW225DEGLINE
		
		SUB DX, DI
		
		CALL DRAW225DEGLINE
		
		ADD DX, SI
		SUB CX, SI
		
		CALL DRAW270DEGLINE
		
		POP CX
		POP DX
		RET
DRAWBOXVERTICAL225DEG ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOXVERTICAL225DEG
;
; Description: Fill a box drawn with DRAWBOXVERTICAL225DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOXVERTICAL225DEG PROC	
		PUSH DI
		PUSH SI
		PUSH DX
		PUSH CX
		DEC CX
		DEC SI
		DEC DI
AGAINFILLBOXVERTICAL225DEG:
		CMP DI, 0
		JE FINISHFILLBOXVERTICAL225DEG
		DEC DI
		CALL DRAW225DEGLINE
		DEC DX
		JMP AGAINFILLBOXVERTICAL225DEG
FINISHFILLBOXVERTICAL225DEG:
		POP CX
		POP DX
		POP SI
		POP DI
		RET
FILLBOXVERTICAL225DEG ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOXVERTICAL225DEG2C
;
; Description: Fill a box drawn with DRAWBOXVERTICAL225DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				AH = Second Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOXVERTICAL225DEG2C PROC	
		PUSH DI
		PUSH SI
		PUSH DX
		PUSH CX
		PUSH BX
		DEC CX
		DEC SI
		MOV BX, 0
AGAINFILLBOXVERTICAL225DEG2C:
		CMP DI, 0
		JE FINISHFILLBOXVERTICAL225DEG2C
		DEC DI
		CMP BX, 0
		JE FILLBOXVERTICAL225DEGCOLOUR2
		MOV BX, 0
		CALL DRAW225DEGLINE
BACKFILLBOXVERTICAL225DEG2C:
		DEC DX
		JMP AGAINFILLBOXVERTICAL225DEG2C
FILLBOXVERTICAL225DEGCOLOUR2:
		MOV BX, 1
		PUSH AX
		MOV AL, AH
		CALL DRAW225DEGLINE
		POP AX
		JMP BACKFILLBOXVERTICAL225DEG2C
FINISHFILLBOXVERTICAL225DEG2C:
		POP BX
		POP CX
		POP DX
		POP SI
		POP DI
		RET
FILLBOXVERTICAL225DEG2C ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWBOXVERTICAL135DEG
;
; Description: Draw a box with vertical and 135 degree lines
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBOXVERTICAL135DEG PROC
		PUSH DX
		PUSH CX

		CALL DRAW90DEGLINE
		CALL DRAW135DEGLINE
		
		SUB DX, DI
		CALL DRAW135DEGLINE
		SUB CX, SI
		SUB DX, SI
		
		CALL DRAW270DEGLINE
		
		POP CX
		POP DX
		RET
DRAWBOXVERTICAL135DEG ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOXVERTICAL135DEG
;
; Description: Fill a box drawn with DRAWBOXVERTICAL135DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOXVERTICAL135DEG PROC
		PUSH DX
		PUSH DI
		PUSH CX
		PUSH SI
		DEC SI
		DEC CX
		DEC DX
		DEC DI
AGAINFILLBOXVERTICAL135DEG:
		CMP DI, 0
		JE FINISHFILLBOXVERTICAL135DEG
		DEC DX
		DEC DI
		CALL DRAW135DEGLINE
		JMP AGAINFILLBOXVERTICAL135DEG
FINISHFILLBOXVERTICAL135DEG:
		POP SI
		POP CX
		POP DI
		POP DX
		RET
FILLBOXVERTICAL135DEG ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOXVERTICAL135DEG2C
;
; Description: Fill a box drawn with DRAWBOXVERTICAL135DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				AH = Second Colour
;				SI = Length
;				DI = Height
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOXVERTICAL135DEG2C PROC
		PUSH DX
		PUSH DI
		PUSH CX
		PUSH SI
		PUSH BX
		DEC SI
		DEC CX
		DEC DX
		DEC DI
		MOV BX, 0
AGAINFILLBOXVERTICAL135DEG2C:
		CMP DI, 0
		JE FINISHFILLBOXVERTICAL135DEG2C
		DEC DI
		DEC DX
		CMP BX, 0
		JE FILLBOXVERTICAL135DEGCOLOUR2
		MOV BX, 0 
		CALL DRAW135DEGLINE
		JMP AGAINFILLBOXVERTICAL135DEG2C
FILLBOXVERTICAL135DEGCOLOUR2:
		MOV BX, 1
		PUSH AX
		MOV AL, AH
		CALL DRAW135DEGLINE
		POP AX
		JMP AGAINFILLBOXVERTICAL135DEG2C
FINISHFILLBOXVERTICAL135DEG2C:
		POP BX
		POP SI
		POP CX
		POP DI
		POP DX
		RET
FILLBOXVERTICAL135DEG2C ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWBOX45DEG135DEG
;
; Description: Draw a box with 45 and 135 degree lines
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBOX45DEG135DEG	PROC
		PUSH DX
		PUSH CX
	
		CALL DRAW225DEGLINE
		
		PUSH SI
		MOV SI, DI
		CALL DRAW135DEGLINE
		POP SI
	
		SUB CX, DI
		SUB DX, DI
		
		CALL DRAW225DEGLINE
		
		ADD DX, SI
		SUB CX, SI
		
		PUSH SI
		
		MOV SI, DI
		CALL DRAW315DEGLINE
		
		POP SI
		
		POP CX
		POP DX
		RET
DRAWBOX45DEG135DEG	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOX45DEG135DEG
;
; Description: Fill a box drawn with DRAWBOX45DEG135DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOX45DEG135DEG	PROC
		PUSH DX
		PUSH SI
		PUSH DI
		PUSH CX
		PUSH BX
		INC DX
		DEC CX
		MOV BX, 1
AGAINFILLBOX45DEG135DEG:
		CMP DI, 0
		JE FINISHFILLBOX45DEG135DEG
		CMP BX, 0
		JE FILLBOX45DEG135DEGDECCOL
		DEC DI	
		DEC DX
		MOV BX, 0
		CALL DRAW225DEGLINE
		JMP AGAINFILLBOX45DEG135DEG	
FILLBOX45DEG135DEGDECCOL:
		DEC CX
		MOV BX, 1
		CALL DRAW225DEGLINE
		JMP AGAINFILLBOX45DEG135DEG	
FINISHFILLBOX45DEG135DEG:
		POP BX
		POP CX
		POP DI
		POP SI
		POP DX
		RET
FILLBOX45DEG135DEG	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLBOX45DEG135DEG2C
;
; Description: Fill a box drawn with DRAWBOX45DEG135DEG
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				AH = Second Colour
;				SI = Length
;				DI = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLBOX45DEG135DEG2C	PROC
		PUSH DX
		PUSH SI
		PUSH DI
		PUSH CX
		PUSH BX
		INC DX
		DEC CX
		MOV BX, 1
AGAINFILLBOX45DEG135DEG2C:
		CMP DI, 0
		JE FINISHFILLBOX45DEG135DEG2C
		CMP BX, 0
		JE FILLBOX45DEG135DEGDECCOL2C
		DEC DI	
		DEC DX
		MOV BX, 0
		CALL DRAW225DEGLINE
		JMP AGAINFILLBOX45DEG135DEG2C
FILLBOX45DEG135DEGDECCOL2C:
		DEC CX
		MOV BX, 1
		PUSH AX
		MOV AL, AH
		CALL DRAW225DEGLINE
		POP AX
		JMP AGAINFILLBOX45DEG135DEG2C
FINISHFILLBOX45DEG135DEG2C:
		POP BX
		POP CX
		POP DI
		POP SI
		POP DX
		RET
FILLBOX45DEG135DEG2C	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWAXONOMETRICCUBOID
;
; Description: Draw an Axonometric cuboid with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWAXONOMETRICCUBOID PROC
		PUSH CX
		PUSH DX
		
		CALL DRAWBOXVERTICAL225DEG
		
		PUSH CX
		PUSH DX
		PUSH DI
		
		SUB DX, DI
		MOV DI, BX
		CALL DRAWBOX45DEG135DEG
		
		POP DI
		POP DX
		POP CX
		
		SUB CX, SI
		ADD DX, SI
		
		PUSH SI
		
		MOV SI, BX
		CALL DRAWBOXVERTICAL135DEG
		
		POP SI
		
		POP DX
		POP CX
		
		RET
DRAWAXONOMETRICCUBOID ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLAXONOMETRICCUBOID
;
; Description: Fill an Axonometric cuboid drawn with DRAWAXONOMETRICCUBOID
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLAXONOMETRICCUBOID	PROC
		PUSH CX
		PUSH DX
		
		CALL FILLBOXVERTICAL225DEG
		
		PUSH CX
		PUSH DX
		PUSH DI
		
		SUB DX, DI
		MOV DI, BX
		CALL FILLBOX45DEG135DEG
		
		POP DI
		POP DX
		POP CX
		
		SUB CX, SI
		ADD DX, SI
		
		PUSH SI
		
		MOV SI, BX
		CALL FILLBOXVERTICAL135DEG
		
		POP SI
		
		POP DX
		POP CX
		
		RET
FILLAXONOMETRICCUBOID	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: FILLAXONOMETRICCUBOID
;
; Description: Fill an Axonometric cuboid drawn with DRAWAXONOMETRICCUBOID
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				AL = Colour
;				AH = Second Colour
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLAXONOMETRICCUBOID2C	PROC
		PUSH CX
		PUSH DX
		
		CALL FILLBOXVERTICAL225DEG2C
		
		PUSH CX
		PUSH DX
		PUSH DI
		
		SUB DX, DI
		MOV DI, BX
		CALL FILLBOX45DEG135DEG2C
		
		POP DI
		POP DX
		POP CX
		
		SUB CX, SI
		ADD DX, SI
		
		PUSH SI
		
		MOV SI, BX
		CALL FILLBOXVERTICAL135DEG2C
		
		POP SI
		
		POP DX
		POP CX
		
		RET
FILLAXONOMETRICCUBOID2C	ENDP


;-------------------------------------------------------------------------------------------------------
; Name: FILLSCREEN
;
; Description: Fill the entire screen with a given color
;
; Input Params:	AL = Colour
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
FILLSCREEN	PROC
		MOV DX, 0
		MOV CX, 0
		MOV SI, 640
		MOV DI, 480
		CALL DRAWBOXVERTICALHORIZONTAL
		CALL FILLBOXVERTICALHORIZONTAL
		RET
FILLSCREEN	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DELAY
;
; Description: Cause a delay
;
; Input Params:
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DELAY	PROC
		PUSH AX
		MOV AX, 0FFH
AGAINDELAY:
		CMP AX, 0
		JE FINISHDELAY
		DEC AX
		JMP AGAINDELAY
FINISHDELAY:
		POP AX
		RET
DELAY	ENDP

;========================================================================================================
; City Centre Specific Subroutines
;========================================================================================================

;-------------------------------------------------------------------------------------------------------
; Name: SETUPVIEW
;
; Description: Sets up the view of the program
;
; Input Params:	
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
SETUPVIEW	PROC
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI

		MOV AL, 18		;640x480 graphics
		MOV AH, 00H		;initialize video mode
		INT	10H
						
		MOV AL, 08H
		CALL FILLSCREEN
		
		MOV CX, 640
		MOV DI, 100
		MOV AL, 07H
		MOV AH, 07H
		CALL DRAWMAINROAD
		
		SUB DI, 10
		SUB CX, DI
		
		MOV BX, 20
		MOV DI, 10
		MOV SI, 40
		MOV AH, 8
		MOV AL, 0FH
		CALL DRAWROADMARKS
		
		CALL DRAWNORMALBUILDINGSTOP
		
		MOV AL, 10
		
		MOV DX, 151
		MOV CX, 639
		MOV AH, 7		
		MOV BX, OFFSET WIDTHS3
		MOV DI, OFFSET HEIGHTS3
		MOV SI,	OFFSET LENGTHS3
		CALL DRAWCITYBUILDINGROW
		
		
		MOV DX, 311
		MOV CX, 639		
		MOV AH, 5	
		MOV BX, OFFSET WIDTHS4
		MOV DI, OFFSET HEIGHTS4
		MOV SI,	OFFSET LENGTHS4
		CALL DRAWCITYBUILDINGROW
		
		MOV BX, 25
		MOV AL, 07H
		MOV AH, 0FH
		CALL DRAWADDITIONALROADS
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		POP AX
		RET
SETUPVIEW	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWNORMALBUILDINGSTOP
;
; Description: Draw the standard normal building top the top of the screen
;
; Input Params:	
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWNORMALBUILDINGSTOP	PROC
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		
		CALL CLEARTOP
		
		MOV AL, 10
		
		MOV DX, 40
		MOV CX, 130
		MOV AH, 2
		MOV BX, OFFSET WIDTHS1
		MOV DI, OFFSET HEIGHTS1
		MOV SI,	OFFSET LENGTHS1
		CALL DRAWCITYBUILDINGROW
					
		MOV DX, 40
		MOV CX, 360
		MOV AH, 5		
		MOV BX, OFFSET WIDTHS2
		MOV DI, OFFSET HEIGHTS2
		MOV SI,	OFFSET LENGTHS2
		CALL DRAWCITYBUILDINGROW
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		POP AX
		RET
DRAWNORMALBUILDINGSTOP	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: CLEAR TOP
;
; Description: Clears buildings at the top of the screen
;
; Input Params:	
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
CLEARTOP	PROC
		PUSH AX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		
		MOV DX, 0
		MOV CX, 440
		MOV SI, 360
		MOV DI, 80
		MOV AL, 08H
		CALL FILLBOX45DEG135DEG
		
		SHl DI, 1
		SUB CX, DI
		MOV DI, CX
		MOV CX, 0
		CALL DRAW90DEG225DEGTRIANGLERIGHT
		CALL FILL90DEG225DEGTRIANGLERIGHT
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP AX
		RET
CLEARTOP	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWCITYBUILDING
;
; Description: Draw a city building with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWCITYBUILDING	PROC
		PUSH AX
		PUSH CX
		PUSH DX
		
		MOV AL, 05H
		MOV AH, 07H
		CALL FILLBOXVERTICAL225DEG2C
		
		PUSH CX
		PUSH DX
		PUSH DI
		
		SUB DX, DI
		MOV DI, BX
		
		MOV AL, 0EH
		MOV AH, 07H
		CALL FILLBOX45DEG135DEG2C
		
		POP DI
		POP DX
		POP CX
		
		SUB CX, SI
		ADD DX, SI
		
		PUSH SI
		
		MOV SI, BX
		
		MOV AL, 07H
		MOV AH, 06H
		CALL FILLBOXVERTICAL135DEG2C
		
		POP SI
		
		POP DX
		POP CX
		
		MOV AL, 0FH
		CALL DRAWAXONOMETRICCUBOID	
		
		POP AX
		RET
DRAWCITYBUILDING	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWCITYBUILDINGROW
;
; Description: Draw a row of city buildings with the given parameters
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Pointer to height array
;				BX = Width
;				AH = Amount of buildings
;				AL = Space between buildings
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWCITYBUILDINGROW	PROC
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
AGAINDRAWCITYBUILDINGROW:
		CMP AH, 0
		JE FINISHDRAWCITYBUILDINGROW
		DEC AH
		
		PUSH DI
		PUSH SI
		PUSH BX
		
		MOV DI, [DI]
		MOV SI, [SI]
		MOV BX, [BX]
		CALL DRAWCITYBUILDING
		
		ADD	DX, SI
		
		PUSH AX
		
		MOV AH, 00H
		ADD DX, AX
		SUB CX, SI
		SUB CX, AX
		
		POP AX
		
		POP BX
		POP SI
		POP DI
		
		
		INC DI
		INC DI
		INC SI
		INC SI
		INC BX
		INC BX
		
		JMP AGAINDRAWCITYBUILDINGROW
FINISHDRAWCITYBUILDINGROW:
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		POP AX
		RET
DRAWCITYBUILDINGROW	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWMAINROAD
;
; Description: Draw the main road for the city
;
; Input Params:	CX = Start Column
;				DI = Width
;				AL = Road Colour
;				AH = Border Colour
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWMAINROAD	PROC
		PUSH DX
		PUSH SI
		MOV	DX, 0
		MOV SI, CX
		
		CALL FILLBOX45DEG135DEG
		
		PUSH AX
		
		MOV AL, AH
		
		CALL DRAWBOX45DEG135DEG
		
		POP AX
		
		POP SI
		POP DX
		RET
DRAWMAINROAD	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWROADMARKS
;
; Description: Draw road marks for the main road
;
; Input Params: CX = Start Column
;				DI = Width
;				SI = Length
;				AL = Colour
;				AH = Amount of Markings
;				BX = Distance between markings
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWROADMARKS PROC
		PUSH AX
		PUSH DX
		PUSH CX
AGAINDRAWROADMARKS:
		CMP AH, 0
		JE FINISHDRAWROADMARKS	
		DEC AH
		CALL FILLBOX45DEG135DEG
		CALL DRAWBOX45DEG135DEG	
		ADD DX, SI
		ADD DX, BX
		SUB CX, SI
		SUB CX, BX
		JMP AGAINDRAWROADMARKS
FINISHDRAWROADMARKS:
		POP CX
		POP DX
		POP BX
		RET
DRAWROADMARKS ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWADDITIONALROADS
;
; Description: Draw road & road marks for additional roads
;
; Input Params: AL = Colour of Road
;				AH = Colour of Markings
;				BX = Width of Markings
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWADDITIONALROADS PROC
		PUSH DX
		PUSH CX
		PUSH DI
		
		MOV DX, 480
		MOV CX, 640
		MOV DI, 130
		CALL DRAW90DEG45DEGTRIANGLELEFT
		CALL FILL90DEG45DEGTRIANGLELEFT
		
		PUSH AX
		
		MOV DI, BX
		MOV AL, AH
		CALL DRAW90DEG45DEGTRIANGLELEFT
		CALL FILL90DEG45DEGTRIANGLELEFT
		
		POP AX
		
		MOV DX, 480
		MOV CX, 0
		MOV DI, 200
		CALL DRAW90DEG45DEGTRIANGLERIGHT
		CALL FILL90DEG45DEGTRIANGLERIGHT
		
		PUSH AX
		
		MOV CX, 185
		MOV DX, 455
		MOV AL, AH
		MOV DI, 55
		MOV SI, 5
		CALL DRAWBOX45DEG135DEG
		CALL FILLBOX45DEG135DEG
		
		POP AX
		
		MOV CX, 0
		MOV DX, 380
		MOV DI, BX
		CALL DRAW315DEG45DEGTRIANGLERIGHT
		
		PUSH AX
		
		MOV AL, AH
		CALL FILL315DEG45DEGTRIANGLERIGHT
		
		POP AX
		
		POP DI
		POP CX
		POP DX
		
		RET
DRAWADDITIONALROADS ENDP

;========================================================================================================
; Subroutines for City Centre Animation
;========================================================================================================

;-------------------------------------------------------------------------------------------------------
; Name: PREPAREREGFORANIMATION
;
; Description: Move to the next animation building
;
; Input Params:	
;
; Output Params: DX = Start Row/Line
;				 CX = Start Column
;				 SI = Length
;				 DI = Height
;				 BX = Width
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
PREPAREREGFORANIMATION	PROC
		MOV DX, 40
		MOV CX, 360
		MOV SI, 50
		MOV DI,	80
		MOV BX,	60
		RET
PREPAREREGFORANIMATION	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: MOVETONEXTANIMATIONBUILDING
;
; Description: Move to the next animation building
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;
; Output Params: DX = Row of next building
;				 CX = Column of next building
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
MOVETONEXTANIMATIONBUILDING	PROC
		PUSH SI
		SHL SI, 1
		ADD DX, SI
		ADD DX, 20
		SUB CX, SI
		SUB CX, 20
		POP SI
		RET
MOVETONEXTANIMATIONBUILDING	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: MOVETOPREVANIMATIONBUILDING
;
; Description: Move to the previous animation building
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;
; Output Params: DX = Row of previous building
;				 CX = Column of previous building
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
MOVETOPREVANIMATIONBUILDING	PROC
		PUSH SI
		SHL SI, 1
		SUB DX, SI
		SUB DX, 20
		ADD CX, SI
		ADD CX, 20
		POP SI
		RET
MOVETOPREVANIMATIONBUILDING	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWBUILDINGSFORANIMATION
;
; Description: Draw a the buildings used for animation
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBUILDINGSFORANIMATION	PROC
		PUSH AX
		PUSH DX
		PUSH CX
		MOV AX, 3
AGAINDRAWBUILDINGSFORANIMATION:
		CMP	AX, 0
		JE FINISHDRAWBUILDINGSFORANIMATION
		DEC AX
		CALL DRAWCITYBUILDING
		CALL MOVETONEXTANIMATIONBUILDING
		JMP AGAINDRAWBUILDINGSFORANIMATION
FINISHDRAWBUILDINGSFORANIMATION:
		POP CX
		POP DX
		POP AX
		RET
DRAWBUILDINGSFORANIMATION	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: ANIMATEBUILDINGS
;
; Description: Animate buildings
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Height
;				BX = Width
;				AL = Amount of loops
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
ANIMATEBUILDINGS	PROC
		PUSH AX
		PUSH DX
		PUSH CX
AGAINANIMATEBUILDINGS:
		CMP AL, 0
		JE FINISHANIMATEBUILDINGS
		DEC AL
		MOV AH, 3
AGAINANIMATEBUILDINGSDOWN:
		CMP AH, 0
		JE FINISHANIMATEBUILDINGSDOWN
		DEC AH
		CALL ANIMATEBUILDINGDOWN
		CALL MOVETONEXTANIMATIONBUILDING
		JMP AGAINANIMATEBUILDINGSDOWN
FINISHANIMATEBUILDINGSDOWN:
		CALL MOVETOPREVANIMATIONBUILDING
		MOV AH, 3
AGAINANIMATEBUILDINGSUP:
		CMP AH, 0
		JE FINISHAGAINANIMATEBUILDINGSUP
		DEC AH
		CALL ANIMATEBUILDINGUP
		CALL MOVETOPREVANIMATIONBUILDING
		JMP AGAINANIMATEBUILDINGSUP
FINISHAGAINANIMATEBUILDINGSUP:
		CALL MOVETONEXTANIMATIONBUILDING
		JMP AGAINANIMATEBUILDINGS
FINISHANIMATEBUILDINGS:
		POP CX
		POP DX
		POP AX
		RET
ANIMATEBUILDINGS	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: ANIMATEBUILDINGDOWN
;
; Description: Animate a building down
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
ANIMATEBUILDINGDOWN	PROC
		PUSH AX
		MOV AX, DI
AGAINANIMATEBUILDINGDOWN:
		DEC AX
		CMP AX, 0
		JE	FINISHANIMATEBUILDINGDOWN
		
		PUSH DI
		PUSH DX
		PUSH CX
		PUSH AX
		PUSH SI
		
		MOV DI, AX
		CALL DRAWCITYBUILDING
		SUB DX, DI
		DEC DX
		MOV AL, 08H
		MOV SI, BX
		CALL DRAW135DEGLINE
		SUB CX, BX
		SUB DX, BX
		INC SI
		CALL DRAW225DEGLINE
		
		POP SI
		POP AX
		POP CX
		POP DX
		POP DI
		
		CALL DELAY
		JMP AGAINANIMATEBUILDINGDOWN
FINISHANIMATEBUILDINGDOWN:
		POP AX
		RET
ANIMATEBUILDINGDOWN	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: ANIMATEBUILDINGUP
;
; Description: Animate a building up
;
; Input Params:	DX = Start Row/Line
;				CX = Start Column
;				SI = Length
;				DI = Height
;				BX = Width
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
ANIMATEBUILDINGUP	PROC
		PUSH AX
		MOV AX, 0
AGAINANIMATEBUILDINGUP:
		INC AX
		CMP AX, DI
		JE	FINISHANIMATEBUILDINGUP
		
		PUSH DI
		
		MOV DI, AX
		CALL DRAWCITYBUILDING
		
		POP DI
		
		CALL DELAY
		JMP AGAINANIMATEBUILDINGUP
FINISHANIMATEBUILDINGUP:
		POP AX
		RET
ANIMATEBUILDINGUP	ENDP


;-------------------------------------------------------------------------------------------------------
; Name: DRAWBUTTONS
;
; Description: Draws buttons for user interaction
;
; Input Params:	
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWBUTTONS	PROC
		PUSH AX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		MOV CX, 5
		MOV DX, 398
		MOV SI, 130
		MOV DI, 20
		MOV AH, 3
AGAINDRAWBUTTONS:
		CMP AH, 0
		JE FINISHDRAWBUTTONS
		DEC AH
		MOV AL, 0FH
		CALL DRAWBOXVERTICALHORIZONTAL
		MOV AL, 00H
		CALL FILLBOXVERTICALHORIZONTAL
		ADD DX, DI
		ADD DX, 10
		JMP AGAINDRAWBUTTONS
FINISHDRAWBUTTONS:
		POP SI
		POP DI
		POP DX
		POP CX
		POP AX
		RET
DRAWBUTTONS	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: SETBUTTONTEXT
;
; Description: Set the text of the buttons
;
; Input Params:	
;
; Output Params: 
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
SETBUTTONTEXT	PROC
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH BP
		
		PUSH DS
		POP ES
		MOV BH, 00H
		MOV AH, 13H
		MOV AL, 01H
		MOV BL, 0FH
		MOV DX, 1901H
		MOV CX, STRDRAWNORM_SZ
		MOV BP, OFFSET STRDRAWNORM
		INT 10H

		MOV DX, 1B01H
		MOV CX, STRDRAWANIM_SZ
		MOV BP, OFFSET STRDRAWANIM
		INT 10H
		

		MOV DX, 1D01H
		MOV CX, STRANIMATEBLD_SZ
		MOV BP, OFFSET STRANIMATEBLD
		INT 10H
		
		POP BP
		POP DX
		POP CX
		POP BX
		POP AX
		RET
SETBUTTONTEXT	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: BUTTON1CLICKED
;
; Description: Check if button 1 was clicked and perform its action
;
; Input Params:	DX = Row clicked on
;				CX = Column clicked on
;
; Output Params: BX = flag if button was clicked
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
BUTTON1CLICKED	PROC
		CMP DX, 398
		JB	NOTBUTTON1
		CMP DX, 418
		JA	NOTBUTTON1
		CMP CX, 5
		JB	NOTBUTTON1
		CMP CX, 135
		JA	NOTBUTTON1
		JMP CLICKEDBUTTON1
NOTBUTTON1:
		MOV BX, 0
		RET
CLICKEDBUTTON1:
		PUSH AX
		MOV AX, 02H
		INT 33H
		CALL CLEARTOP
		CALL DRAWNORMALBUILDINGSTOP
		MOV BX, 1
		MOV AX, 01H
		INT 33H
		POP AX
		RET
BUTTON1CLICKED	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: BUTTON2CLICKED
;
; Description: Check if button 2 was clicked and perform its action
;
; Input Params:	DX = Row clicked on
;				CX = Column clicked on
;
; Output Params: BX = flag if button was clicked
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
BUTTON2CLICKED	PROC
		CMP DX, 428
		JB	NOTBUTTON2
		CMP DX, 448
		JA	NOTBUTTON2
		CMP CX, 5
		JB	NOTBUTTON2
		CMP CX, 135
		JA	NOTBUTTON2
		JMP CLICKEDBUTTON2
NOTBUTTON2:
		MOV BX, 0
		RET
CLICKEDBUTTON2:
		PUSH AX
		MOV AX, 02H
		INT 33H
		
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		
		CALL CLEARTOP
		CALL PREPAREREGFORANIMATION
		CALL DRAWBUILDINGSFORANIMATION
		MOV AL, 1
		CALL ANIMATEBUILDINGS
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		
		MOV BX, 1
		MOV AX, 01H
		INT 33H
		POP AX
		MOV BX, 1
		RET
BUTTON2CLICKED	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: BUTTON3CLICKED
;
; Description: Check if button 3 was clicked and perform its action
;
; Input Params:	DX = Row clicked on
;				CX = Column clicked on
;
; Output Params:
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
BUTTON3CLICKED	PROC
		CMP DX, 458
		JB	NOTBUTTON3
		CMP DX, 478
		JA	NOTBUTTON3
		CMP CX, 5
		JB	NOTBUTTON3
		CMP CX, 135
		JA	NOTBUTTON3
		JMP CLICKEDBUTTON3
NOTBUTTON3:
		RET
CLICKEDBUTTON3:
		PUSH AX
		MOV AX, 02H
		INT 33H
		
		CALL CARANIMATION
		CALL CLEARROAD
		
		MOV AX, 01H
		INT 33H
		POP AX
		RET
BUTTON3CLICKED	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: DRAWEXITBUTTON
;
; Description: Draw the exit button at the top right corner
;
; Input Params:
;
; Output Params:
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
DRAWEXITBUTTON	PROC
		PUSH AX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		
		MOV CX, 619
		MOV DX, 1
		MOV DI, 20
		MOV SI, 20
		MOV AL, 0FH
		CALL DRAWBOXVERTICALHORIZONTAL
		MOV AL, 04H
		CALL FILLBOXVERTICALHORIZONTAL
		MOV AL, 0FH
		CALL DRAW315DEGLINE
		ADD CX, SI
		CALL DRAW225DEGLINE
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP AX
DRAWEXITBUTTON	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: EXITBUTTONCLICKED
;
; Description: Check if exit button was clicked
;
; Input Params:	DX = Row clicked on
;				CX = Column clicked on
;
; Output Params: BX = flag if button clicked
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
EXITBUTTONCLICKED	PROC
		CMP DX, 1
		JB NOTEXITBUTTON
		CMP DX, 21
		JA	NOTEXITBUTTON
		CMP CX, 619
		JB	NOTEXITBUTTON
		CMP CX, 639
		JA	NOTEXITBUTTON
		JMP EBCLICKED
NOTEXITBUTTON:
		MOV BX, 0
		RET
EBCLICKED:
		MOV BX, 1
		RET
EXITBUTTONCLICKED	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: CARANIMATION
;
; Description: Draws a 'car' and performs a 'drive' animation
;
; Input Params:
;
; Output Params:
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
CARANIMATION	PROC
		PUSH AX
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		MOV AH, 250
		MOV	DX, 90
		MOV CX, 410
		MOV AL, 00H
		MOV SI, 50
		MOV DI, 10
		MOV BX, 10
AGAINCAR:
		CMP AH, 0
		JE FINISHCAR
		DEC AH
		
		MOV AL, 07H
		CALL DRAW90DEGLINE
		
		PUSH DX
		PUSH CX
		PUSH SI
		
		SUB DX, DI
		MOV SI, BX
		INC SI
		CALL DRAW135DEGLINE
		DEC CX
		CALL DRAW135DEGLINE
		
		POP SI
		POP CX
		POP DX
		
		INC DX
		DEC CX
		MOV AL, 00H
		CALL DRAWAXONOMETRICCUBOID
		MOV AL, 00H
		CALL FILLAXONOMETRICCUBOID
		CALL DELAY
		JMP AGAINCAR
FINISHCAR:
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		POP AX
		RET
CARANIMATION	ENDP

;-------------------------------------------------------------------------------------------------------
; Name: CLEARROAD
;
; Description: Clears the bottom of the road from any 'cars'
;
; Input Params:
;
; Output Params:
;
; Registers Affected: 
;-------------------------------------------------------------------------------------------------------
CLEARROAD	PROC
		PUSH BX
		PUSH CX
		PUSH DX
		PUSH DI
		PUSH SI
		
		MOV DX, 332
		MOV CX, 170
		MOV SI, 60
		MOV DI, 30
		MOV AL, 07H
		CALL FILLBOX45DEG135DEG
		
		POP SI
		POP DI
		POP DX
		POP CX
		POP BX
		RET 
CLEARROAD	ENDP

CSEG    ENDS
        END     MAIN


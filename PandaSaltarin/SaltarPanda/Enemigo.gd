extends Area2D

var avance=true
var velocidad_y=0.8

func _ready():
	pass

func _physics_process(delta):
	if (avance):
		position.y+=velocidad_y



func _on_VisibilityEnabler2D_screen_exited():
	queue_free()

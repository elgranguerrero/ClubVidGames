extends KinematicBody2D

func _ready():
	pass

func _physics_process(delta):
	move_and_collide(Vector2(0,1))
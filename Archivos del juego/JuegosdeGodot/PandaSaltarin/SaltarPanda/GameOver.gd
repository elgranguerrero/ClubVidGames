extends Node2D

func _ready():
	pass


func _on_btn_Salir_pressed():
	get_tree().change_scene("res://Escenas/GUI_Inicio.tscn")
	get_tree().paused=false

func _on_btn_Retry_pressed():
	get_tree().reload_current_scene()
	get_tree().paused=false

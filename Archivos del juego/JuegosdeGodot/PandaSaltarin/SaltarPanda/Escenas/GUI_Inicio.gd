extends Node2D

func _ready():
	pass


func _on_btn_Empezar_pressed():
	get_tree().change_scene("res://Main.tscn")


func _on_btn_Salir_pressed():
	get_tree().quit()

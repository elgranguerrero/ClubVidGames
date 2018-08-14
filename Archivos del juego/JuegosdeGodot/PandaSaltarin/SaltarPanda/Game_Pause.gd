extends Sprite

func _ready():
	pass



func _on_Btn_Play_pressed():
	get_tree().paused=false
	get_parent().get_node("IntroPrueva").play()
	queue_free()


func _on_Btn_Retry_pressed():
	get_tree().reload_current_scene()
	get_tree().paused=false


func _on_Btn_Menu_pressed():
	get_tree().change_scene("res://Escenas/GUI_Inicio.tscn")
	get_tree().paused=false

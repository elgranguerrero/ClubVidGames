extends Position2D

var pos=[169,296]

export (PackedScene) var escudoPrueba
export (PackedScene) var vidaPrueba
export (PackedScene) var tiempoPrueba

func _ready():
	pass


func _on_Timer_timeout():
	
	var randomPos=randi()%2
	var randomItem=randi()%3
	var newItem
	if (randomItem==0):
		#newItem=shield.instance()
		newItem=escudoPrueba.instance()
	elif (randomItem==1):
		newItem=tiempoPrueba.instance()
	else:
		newItem=vidaPrueba.instance()
	
	get_tree().get_nodes_in_group("main")[0].add_child(newItem)
	position.x=pos[randomPos]
	newItem.global_position=global_position



func _on_ControlTiempo_timeout_Item():
	get_node("TimerItem").start()

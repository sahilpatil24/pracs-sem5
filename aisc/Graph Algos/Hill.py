#in hill climbing, we had to go to the node that has the highest value and the 
#value is better than the current value , if no such node is found then return

graph = {
    "A" : ["B","C"],
    "B" : ["A","D"],
    "C" : ["A","D"],
    "D" : ["B","C"]
}

values = {
    "A" : 10,
    "B" : 20,
    "C" : 5,
    "D" : 50
}

def find_best(node):
    #i know the logic
    #we get the neighbours of the current node
    #we keep a max variable
    #we find the maximum of the neighbours
    #keep a track of which node had the highest value
    #maximum = max(graph.get(node), values.get(node)) #idk how to run the foreach loop
    best = node
    for neighbour in graph[node]:
        if values[neighbour] > values[best]:
            best = neighbour
    return best


def hill(node, goal):
    #keep finding the best neighbour till we reach the goal state
    #or the value of neigbour is not greater than the current node
    if node == goal:
        return node
    best = find_best(node)
    if values[best] < values[node]:
        return node
    else:
        return hill(best, goal)

hill("A","D")
        

    


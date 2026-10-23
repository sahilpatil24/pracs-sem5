R = [
    [0.6,0.3,0.9],
    [0.4,0.2,0.8]
]

S = [
    [0.6,0.3],
    [0.12,0.7],
    [0.4,0.2]
]

T = [[0, 0] for i in range(len(R))]
for i in range(len(R)):
    for k in range(len(S[0])):
        
        maximum = 0
        for j in range(len(R[0])):
            minimum = min(R[i][j], S[j][k]) #for min max composition
            #minimum = (R[i][j] * S[j][k]) #for max product composition

            maximum = max(maximum, minimum)
        T[i][k] = maximum

# for i in range(len(T)):
print(T)
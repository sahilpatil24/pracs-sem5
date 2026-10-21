import matplotlib.pyplot as plt
import numpy as np
#step 1 calculate the distance
N = 7
R = 3
D = R * np.sqrt(3 * N)

print(D)

#used for printing the red line
centers = []

#step 2 take the center values x and y
for u in range(-2,3):
    for v in range(-2,3):

        x = np.sqrt(3) * R * u + 3/2 * R * v
        y = 3/2 * R * v

        #Step 3 Assign the frequencies

        freq = (2 * u + 3 * v) % N

        #step 4 append the centers of the co channel cells
        if freq == 0:
            centers.append((x,y))

        #step 5 calculate the six corners of the hexagonal cell
        angles = np.linspace(0, 2*np.pi, 7)
        hx = x + R * np.cos(angles + np.pi/6)
        hy = y + R * np.sin(angles + np.pi/6)
        
        #step 6 draw the hexagonal cells
        plt.fill(hx,hy,color= plt.cm.Set3(freq/ N))

        plt.text(x,y, f"F{freq + 1}",ha = "center", va = "center")

#draw the red line
p1 = centers[0]
p2 = centers[1]

plt.plot(
    [p1[0],p2[0]],
    [p1[1],p2[1]],
    "r-o"
)

plt.title("Frequency Reuse")
plt.gca().set_aspect("equal")
plt.axis("off") #to turn off the axis
plt.show()

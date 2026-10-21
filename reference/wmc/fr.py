import matplotlib.pyplot as plt
import numpy as np

#calculate the d
n = 7
R = 3
d = np.sqrt(3 * n) * R

centers = []

#centers calculation
for u in range(-2,3):
    for v in range(-2,3):

        x = np.sqrt(3) * R * u + 3/2 * R * v
        y = R * v * 3/2

        #freq cal
        freq = (2 * u + 3 * v) % n

        if freq == 0:
            centers.append((x,y))
            
        #calculate the hx and hy and six corners
        angles = np.linspace(0, 2*np.pi, 7)
        hx = x + R * np.cos(angles + np.pi / 6)
        hy = y + R * np.sin(angles + np.pi / 6)

        plt.fill(hx, hy, color = plt.cm.Set3(freq/n))
        plt.text(x, y, f"F{freq + 1}")

p1 = centers[0]
p2 = centers[1]
plt.plot(
    [p1[0], p2[0]],
    [p1[1], p2[1]]
)
plt.axis("off")
plt.show()

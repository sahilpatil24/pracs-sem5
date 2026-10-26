import matplotlib.pyplot as plt
import numpy as np 

def sq(n):
    return np.sqrt(n)

n = 7
r = 3
d = sq(3 * n) * r 
print(d)

centers = []

for u in range(-2, 3):
    for v in range(-2, 3):

        x = sq(3)*r*u + sq(3)/2*r*v
        y = 3/2 * r * v

        freq = (2 * u + 3 * v) % n

        if freq == 0:
            centers.append((x,y))

        angles = np.linspace(0,2*np.pi,n)
        hx = x + r * np.cos(angles + np.pi/6)
        hy = y + r * np.sin(angles + np.pi/6)

        plt.fill(hx,hy, color = plt.cm.Set3(freq/n))
        plt.text(x,y, f"F{freq+1}",ha = "center", va = "center")

p1 = centers[0]
p2 = centers[1]

plt.plot(
    [p1[0],p2[0]],
    [p1[1],p2[1]],
    "r-o"
)

plt.title("Freq reuse")
plt.axis("off")
plt.show()

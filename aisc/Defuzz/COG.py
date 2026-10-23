def trap(x,a,b,c,d):
    return max(0, min((x-a)/(b-a), 1, (d-x)/(d-c)))

area = 0
momentum = 0
for i in range(10001):
    dx = 0.1
    x = i * dx

    c1 = 0.75 * trap(x,0,10,20,30)
    c2 = 1 * trap(x,40,50,60,70)
    c3 = 0.5 * trap(x,80,90,100,110)

    membership = max(c1,c2,c3)

    area += membership * dx
    momentum += x * membership * dx

centroid = momentum/area

print("centroid is ", centroid)
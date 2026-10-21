data = "11"
bc = "10110111000"
n = len(bc)
m = len(data)

xor_result = ""
#transmitter side
for i in range(0,m):
    for j in range(0,n):
        xor = int(data[i]) ^ int(bc[j])
        xor_result += str(xor)

print(xor_result)
#receiver side
count = 0
received = "0100100011101001000111"
for i in range(0,m):
    start = i * n
    end = start + n
    decision = 0
    block = received[start:end]
    for j in range(0, n):
        xor = int(block[j]) ^ int(bc[j])
        if xor == 1:
            count = count + 1

    if count >= n/2:
        decision = 1
    
    print(decision)

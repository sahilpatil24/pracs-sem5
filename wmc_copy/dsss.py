data = "01"
bc = "10110111000"

spread = ""
for i in range(0,len(data)):
    for j in range(0,len(bc)):
        xor = int(data[i]) ^ int(bc[j])
        spread += str(xor)

received = "1011011100001001000111"
for i in range(len(data)):
    start = i * len(bc)
    end = start + len(bc)
    received_block = received[start:end]
    count = 0
    for j in range(len(received_block)):
        xor = int(received_block[j]) ^ int(bc[j])
        if xor == 1:
            count += 1
    
    if count < len(bc)/2:
        decision = 0
        print(decision)
    else:
        decision = 1
        print(decision)
    
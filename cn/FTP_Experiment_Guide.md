# Computer Networks Practical: FTP Server Configuration and File Transfer

## 1. Experiment aim

To configure an FTP server, connect to it from a client, authenticate using an allowed account, and demonstrate remote directory operations, file upload, and file download.

## 2. Short flow to remember

1. **Prepare the server:** install `vsftpd`.
2. **Configure the server:** edit `/etc/vsftpd.conf` and enable only the access modes needed for the practical.
3. **Restart and verify:** restart the service and check its status.
4. **Find the server IP:** use `ip addr` or `ifconfig`.
5. **Connect from the client:** run `ftp <SERVER_IP>`.
6. **Authenticate:** use anonymous login only if configured, or use the Linux account created for the practical.
7. **Operate remotely:** `pwd`, `ls`, and `mkdir`.
8. **Transfer files:** `put <local-file>` uploads; `get <remote-file>` downloads.
9. **Verify and disconnect:** check transfer messages and use `bye`.

**Mental model:** server setup → IP address → client connection → login → directory operations → upload/download → disconnect.

## 3. Topology and roles

- **FTP server:** computer running the `vsftpd` service. It accepts FTP connections and exposes permitted directories.
- **FTP client:** computer initiating the connection and issuing FTP commands.
- **Server IP:** address the client uses to reach the server. Use the current lab IP; example addresses can change between sessions.
- **Local path:** path on the client computer.
- **Remote path:** path on the server computer.

Example labels used throughout this guide: server account `ftpstudent`, client-side file `message.txt`, and server-side file `welcome.py`. These are examples only; use your own lab values.

## 4. Server-side procedure

### Step 1 — Install the FTP server

```bash
sudo apt-get install vsftpd
```

`sudo` runs the command with elevated privileges. `apt-get install` installs the package, and `vsftpd` is the FTP server software.

### Step 2 — Open the configuration file

```bash
sudo nano /etc/vsftpd.conf
```

Settings commonly relevant to this practical:

- `anonymous_enable`: permits or blocks anonymous FTP login.
- `local_enable`: permits or blocks logins using local Linux accounts.
- `write_enable`: allows FTP write operations, subject to other server and filesystem permissions.

The exact settings required depend on whether the exercise tests anonymous access, local-user access, or both. Do not enable anonymous write access just to make transfers work. Save the file in Nano with **Ctrl+O**, press **Enter**, then exit with **Ctrl+X**.

### Step 3 — Restart and check the service

```bash
sudo systemctl restart vsftpd
sudo systemctl status vsftpd
```

On systems using the older service command, this may also work:

```bash
sudo service vsftpd restart
```

Restarting reloads the service configuration. If it fails, inspect the status output and logs rather than repeatedly restarting.

### Step 4 — Find the server IP

```bash
ip addr
```

Alternatively, if installed:

```bash
ifconfig
```

Find the IPv4 address (`inet`) on the active network interface. Do not use `127.0.0.1` when connecting from another computer; that is the loopback address of the current computer.

### Step 5 — Create a local Linux user (for authenticated FTP)

```bash
sudo adduser ftpstudent
```

Set a password and complete the prompts. This creates a Linux account and usually a home directory such as `/home/ftpstudent`.

Creating the account alone does **not** guarantee FTP access. The FTP configuration must allow local users, and account restrictions, directory permissions, and network settings must also permit access.

## 5. Client-side procedure

### Step 6 — Connect to the server

```bash
ftp <SERVER_IP>
```

Replace `<SERVER_IP>` with the server's current address, for example `192.168.1.20` only if that is actually the address in your lab.

At the username prompt:

- Enter `anonymous` only if anonymous access is enabled and required; or
- Enter the Linux username created on the server, such as `ftpstudent`, and then its password.

A successful connection commonly displays FTP response codes such as:

- `220`: service ready.
- `331`: username accepted; password is required.
- `230`: login successful.

These are server response codes, not commands you type.

### Step 7 — Inspect and create remote directories

At the `ftp>` prompt:

```text
pwd
ls
mkdir cn_prac
ls
```

- `pwd`: displays the current remote working directory.
- `ls`: lists remote files and directories.
- `mkdir cn_prac`: creates a directory on the remote server, if permitted.

These commands are entered at `ftp>`, not at the ordinary Linux shell prompt.

### Step 8 — Prepare a local file on the client

Exit the FTP session if needed, then on the client's normal terminal create a file:

```bash
cat > message.txt
```

Type a short message, then press **Ctrl+D** to save/finish input. Alternatively, create the file using a text editor. Ensure you know which local directory contains the file before running `ftp`.

### Step 9 — Upload from client to server

Connect and log in again if necessary. At the `ftp>` prompt, make sure the remote directory is the intended destination, then run:

```text
put message.txt
ls
```

`put` transfers a file from the client's local working directory to the server's current remote directory. The local file must exist, and the account needs write permission at the destination.

### Step 10 — Prepare and download a server file

On the server's normal terminal, create a test file in the remote directory that the FTP account can access. For example:

```bash
sudo -u ftpstudent sh -c 'printf "FTP download test\n" > /home/ftpstudent/welcome.py'
```

This example writes a small text payload to a file named `welcome.py`; the `.py` extension is just a filename and the content is not necessarily executable Python code.

Now, from the client, connect and log in. Navigate to the directory containing the file if required, then enter:

```text
get welcome.py
```

`get` downloads a remote file into the client's current local working directory. Use `ls` at the FTP prompt to inspect remote contents; use `!ls` in many command-line FTP clients to inspect local contents, or exit FTP and use `ls` in the local terminal.

A completed transfer often ends with response `226`, meaning the requested data-transfer action completed.

### Step 11 — Disconnect

At the FTP prompt:

```text
bye
```

This ends the FTP session and returns to the normal terminal.

## 6. Command quick reference

| Command | Where to run it | Purpose |
|---|---|---|
| `sudo apt-get install vsftpd` | Server shell | Install FTP server software |
| `sudo nano /etc/vsftpd.conf` | Server shell | Edit server configuration |
| `sudo systemctl restart vsftpd` | Server shell | Restart service |
| `sudo systemctl status vsftpd` | Server shell | Check service state |
| `ip addr` / `ifconfig` | Server shell | Find server IP |
| `sudo adduser ftpstudent` | Server shell | Create Linux account |
| `ftp <SERVER_IP>` | Client shell | Connect to server |
| `pwd` | FTP prompt | Show remote directory |
| `ls` | FTP prompt | List remote files |
| `mkdir <directory>` | FTP prompt | Create remote directory |
| `put <file>` | FTP prompt | Upload local file |
| `get <file>` | FTP prompt | Download remote file |
| `bye` | FTP prompt | Disconnect |

## 7. Common mistakes and troubleshooting

- **Connection refused / timeout:** verify the server IP, that both computers can reach each other, and that `vsftpd` is running. A firewall may block FTP.
- **Login fails:** verify the username and password; check `local_enable` or `anonymous_enable` according to the intended login mode, plus any account restrictions.
- **`put` fails:** confirm the file exists in the client's local working directory and that the server destination is writable.
- **`get` fails:** confirm the remote filename/path is correct and the account can read the file.
- **Directory creation fails:** the account may lack write permission in the current remote directory.
- **Service restart fails:** inspect `sudo systemctl status vsftpd` and system logs; a syntax error or conflicting setting may prevent startup.
- **Wrong directory:** use FTP `pwd` and `ls` to check the remote location before transferring.
- **Confusing local and remote commands:** before login, commands run in the Linux shell; after login, commands at `ftp>` operate through the FTP session.

## 8. FTP response codes to recognize

| Code | Typical meaning |
|---|---|
| `220` | FTP service ready |
| `331` | Password required |
| `230` | Login successful |
| `150` | Server is opening a data connection / starting transfer |
| `226` | Transfer or requested data action completed |
| `257` | Directory action completed; often shown after `mkdir` |

Exact messages can vary by FTP client and server version.

## 9. Viva questions and short answers

**1. What is FTP?**  
File Transfer Protocol is an application-layer protocol for transferring files between a client and a server.

**2. What is `vsftpd`?**  
A commonly used FTP server implementation for Linux; its name stands for “Very Secure FTP Daemon.”

**3. What is the role of the server?**  
It listens for FTP connections, authenticates users according to its configuration, and provides permitted file and directory access.

**4. What is the role of the client?**  
It connects to the server and issues commands to navigate, upload, or download files.

**5. What is anonymous FTP?**  
A mode that permits login using the special username `anonymous` when the server administrator has enabled it.

**6. What does `/etc/vsftpd.conf` do?**  
It stores configuration options for the `vsftpd` service, including login and write-access behaviour.

**7. Difference between `put` and `get`?**  
`put` uploads from client to server; `get` downloads from server to client.

**8. What do `pwd`, `ls`, and `mkdir` do in FTP?**  
They show the remote working directory, list remote contents, and create a remote directory, respectively.

**9. What do codes 220, 230, and 226 mean?**  
220 means service ready, 230 means login successful, and 226 indicates a data-transfer action completed.

**10. Does adding a Linux user automatically enable FTP?**  
No. Server configuration, authentication rules, account restrictions, filesystem permissions, and network access also matter.

**11. Is traditional FTP encrypted?**  
No. Traditional FTP does not encrypt credentials or transferred data. FTPS and SFTP are different approaches to secure file transfer; SFTP is based on SSH and is not the FTP protocol.

**12. Why do we use an IP address?**  
The client uses the server's network address to identify which computer to connect to.

## 10. Conclusion

The FTP server was configured and started, its IP address was identified, and a client connected using an allowed login method. Remote directory commands were demonstrated, followed by a client-to-server upload using `put` and a server-to-client download using `get`. Successful transfer responses and the resulting files verify the main objective.

## 11. Safety and accuracy notes

- Use the IP address assigned in the current lab, not an old screenshot's address.
- Only enable the login and write permissions required for the practical.
- Do not expose an FTP server to an untrusted network with weak credentials.
- Traditional FTP sends credentials and data without encryption. For real deployments, prefer a properly configured secure alternative such as SFTP or FTPS.
- Some lab machines use `ifconfig` and `service`; newer Linux systems commonly provide `ip addr` and `systemctl`.

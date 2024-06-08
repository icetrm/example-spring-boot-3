## Spring Boot 3 - Services
Create REST APIs and work with MySQL databases.

- [2024-02-05] security, JPA, mysql, SecurityController
- [2024-02-19] log4j2
- [2024-02-28] @Test @DataJpaTest @Mock @InjectMocks

## API Reference

### Security Controller

```http
  POST /auth/jwt/login
```
#### Body Json
```http
  {
    "username": "guest1",
    "password": "admin"
  }
```
#### Response
```http
  {
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWVzdDEiLCJyb2xlcyI6WyJndWVzdCJdLCJpc3MiOiJUb2RheSIsImlhdCI6MTcxMDgyNzc1NiwiYXVkIjpbInVua25vd24iXSwiZXhwIjoxNzEwODMxMzU2fQ.ydL154EkbGnX6i2v1toy1ywXYAVLVDrGMD2KJk171XI",
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWVzdDEiLCJzY29wZXMiOlsicmVmcmVzaF90b2tlbiJdLCJqdGkiOiJjMDk5MGU0Yy03MmQwLTRkOTMtOTllZi1iMjU3N2I0MTg4YWQiLCJpc3MiOiJUb2RheSIsImlhdCI6MTcxMDgyNzc1NiwiYXVkIjpbInVua25vd24iXSwiZXhwIjoxNzEwODM0OTU2fQ.t99qyQxerzlRAiZMDRS40blZQkplwDGgp0_hGHuTGlo"
  }
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your username |
| `password` | `string` | **Required**. Your password |

##
```http
  POST /auth/jwt/logout
```
| Header | Type     | Description              |
| :-------- | :------- |:-------------------------|
| `Authorization` | `string` | **Required**. Your token |

##
```http
  POST /auth/jwt/refresh
```
| Header | Type     | Description              |
| :-------- | :------- |:-------------------------|
| `Authorization` | `string` | **Required**. Your token |

##
```http
  GET /auth/jwt/info
```
| Header | Type     | Description              |
| :-------- | :------- |:-------------------------|
| `Authorization` | `string` | **Required**. Your token |

### Service Controller
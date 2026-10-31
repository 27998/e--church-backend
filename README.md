# PROJECT NAME: e--church-backend
### STUDENT NAME: SHEMA Vicent
### STUDENT ID: 27998


## Endpoints

Base paths: `/api/ministries`, `/api/parishes`, `/api/pastors`, `/api/members`, `/api/service-requests`.

### Part A: CRUD

| Method | Path |
|---|---|
| POST | `/api/{path}/save` |
| GET | `/api/{path}/all` |
| GET | `/api/{path}/{id}` |
| PUT | `/api/{path}/update/{id}` |
| DELETE | `/api/{path}/delete/{id}` |

### Part B: derived queries

| ID | Endpoint |
|---|---|
| B1 | `GET /api/members/by-last-name?lastName=mukeshimana` |
| B2 | `GET /api/service-requests/by-status?status=REQUESTED` |
| B3 | `GET /api/service-requests/between?start=2026-10-01&end=2026-10-31` |

### Part C: JPQL queries

| ID | Endpoint |
|---|---|
| C1 | `GET /api/pastors/by-ministry?name=youth` |
| C2 | `GET /api/parishes/free` |
| C3 | `GET /api/ministries/unused` |
| C4 | `GET /api/members/of-pastor/{pastorId}` |

### Part D: Business rules

| ID | Endpoint |
|---|---|
| D1 | `POST /api/service-requests/save` (rejects past dates and busy pastors, always starts as REQUESTED) |
| D2 | `PATCH /api/service-requests/{id}/status?status=APPROVED` |
| D3 | `PUT /api/pastors/{pastorId}/ministries/{ministryId}` |
| D4 | `PATCH /api/pastors/{pastorId}/parish?parishNumber=4` |

Status flow: REQUESTED to APPROVED or DECLINED; APPROVED to HELD or DECLINED; HELD and DECLINED are final.

### Part E: service records

| ID | Endpoint |
|---|---|
| E1 | `POST /api/service-records/save?serviceRequestId={id}` |
| E2 | `GET /api/service-records/member/{memberNumber}` |
| E3 | `DELETE /api/service-records/delete/{id}` and `DELETE /api/service-requests/delete/{id}` (blocked while the request has service records) |

### Part F: reports and bulk updates

| ID | Endpoint |
|---|---|
| F1 | `GET /api/service-requests/stats/by-status` |
| F2 | `PATCH /api/service-requests/cancel-day?pastorId={id}&date=2026-10-20` |

## Example request body

`POST /api/service-requests/save`
```json
{
  "serviceDate": "2026-12-01",
  "serviceType": "Wedding",
  "member": { "id": "MEMBER-ID" },
  "pastor": { "id": "PASTOR-ID" }
}
```

## Project structure

```
src/main/java/com/auca/echurch
  controller/   REST controllers
  service/      business logic
  repository/   Spring Data JPA repositories
  model/        entities and the ServiceStatus enum
```

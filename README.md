# 🌎 CEP Lookup App
- Discipline: Mobile Device Programming
- Teacher: Junio Moreira
- Student: João Augusto Marciano Silva
- Final date: 05/10/2025

## Application operation

| Initial Screen | Query Example | Error Example |
|:---:|:---:|:---:|
| <img height="500" alt="image" src="https://github.com/user-attachments/assets/76394c32-1a2a-4572-beec-7416a2bc7bd7" /> | <img height="500" alt="image" src="https://github.com/user-attachments/assets/15dd635f-7ad1-44ee-b460-2daaf685b971" /> | <img height="500" alt="image" src="https://github.com/user-attachments/assets/838a20ff-c084-4dc2-ac80-7758c6597932" /> |




- The application starts with a field for CEP (postal code) insertion.
- It provides address lookup from a valid CEP:
  - The user must enter an 8-digit CEP.
  - The text field prevents the insertion of more than 8 characters.
- The **SEARCH ADDRESS** button performs the query on the public **ViaCEP** API.
- The result is displayed in a text field below, showing:
  - Street
  - Neighborhood
  - City
  - State
- Invalid or unfound CEPs return an **"Error"** message.
- The application handles internet connection failures, informing the user about the error.

## Download

[APK](https://www.youtube.com/watch?v=5mI_zN8rGRk)
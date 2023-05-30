# Room Booking App
<img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/iv_launcher.svg" align="left" width="90">
This repository contains an Android application designed to simplify the process of booking and managing rooms for various purposes. 
This project was developed as part of a week-long hackathon-style exam at the end of the Android Development course to demonstrate the skills and apply the knowledge gained during the course.

## Minimal requirements and features
- Supports API >= 26
- User Registration and Authorization
- Room Details and Visuals
- Booking Management
- Administrative dashboard

## Samples
| Sign up and sign in | Adding a new room | Adding a new role |
| --- | --- | --- |
| <img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/gifs/1_SignUp_SignIn.gif" width="240" height="520"/> | <img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/gifs/2_Add_new_room.gif" width="240" height="520"/> | <img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/gifs/3_All_users.gif" width="240" height="520"/> |

| Room details and booking | Room delete and sign out |
| --- | --- |
| <img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/gifs/4_Room_details_and_booking.gif" width="240" height="520"/> | <img src="https://github.com/krymov-d/Booking_App_Demo/blob/dev/samples/gifs/5_Room_delete_and_SignOut.gif" width="240" height="520"/> |

## Instalation
- Clone the following project for backend services: https://github.com/krymov-d/JS-Hackathon-RoomBooking-Backend
- Next, you will need to build and run the Docker image:
```
docker build -t image_name .
docker run -d -p 8087:8087 image_name
```
- Clone this project, open it in the Android Studio
- Select the device you wish to run the app on (for example the emulator) and launch

## Developer contacts
Email: krymov.dulat@gmail.com 

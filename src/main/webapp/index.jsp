<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KMS | Student Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .hero-card {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 3rem;
            border: 1px solid rgba(255, 255, 255, 0.2);
            box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.37);
            text-align: center;
        }
        .btn-custom {
            padding: 12px 40px;
            font-weight: 600;
            border-radius: 50px;
            transition: 0.3s;
        }
    </style>
</head>
<body>
    <div class="hero-card">
        <h1 class="display-4 fw-bold mb-3">Student Management System</h1>
        <p class="lead mb-4">Efficiently managing Students, Teachers, and Courses.</p>
        <hr class="bg-white">
        <div class="mt-4">
            <a href="login.html" class="btn btn-light btn-custom me-2">Login</a>
            <a href="register.html" class="btn btn-outline-light btn-custom">Sign Up</a>
        </div>
    </div>
</body>
</html>
const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const data = {
    username: form.username.value,
    password: form.password.value,
  };

  const jsonData = JSON.stringify(data);

  fetch("http://localhost:8080/api/auth/signin", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: jsonData,
    credentials: "include", // Bao gồm thông tin xác thực để lưu cookie
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Success:", data);

      // Log các cookies hiện tại
      console.log("Current cookies:", document.cookie);

      // Thực hiện các thao tác cần thiết với dữ liệu

      // Đặt một timeout ngắn để đảm bảo console logs được hiển thị
      setTimeout(() => {
        // Chuyển hướng tới trang /resource
        window.location.href = "/resource";
      }, 1000); // Đợi 1 giây trước khi chuyển hướng
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});

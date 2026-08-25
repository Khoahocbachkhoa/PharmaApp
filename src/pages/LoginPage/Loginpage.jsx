import { FaUser, FaLock } from "react-icons/fa";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginApi } from "../../api/authApi";

import styles from "../../styles/LoginPage.module.css";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    setError("");

    try {
      const res = await loginApi(username, password);
      const { loggedIn } = res.data;

      if (loggedIn == "true" || loggedIn == true) {
        localStorage.setItem("loggedIn", loggedIn);
        navigate("/dashboard");
      } else {
        setError("Đăng nhập thất bại");
      }

    } catch (err) {
      if (err.response) {
        setError(err.response.data.msg);
      } else {
        setError("Lỗi server");
      }
    }
  };

  return (
    <div className={styles.pageWrapper}>
      <div className={styles.loginCard}>
        <h2 className={styles.loginTitle}>Đăng nhập</h2>

        <form className={styles.form} onSubmit={handleLogin}>
          <div className={styles.inputGroup}>
            <label className={styles.inputLabel}>Tên đăng nhập</label>

            <div className={styles.inputField}>
              <FaUser className={styles.FaUserLock} />

              <input
                className={styles.input}
                type="text"
                placeholder="Nhập tên đăng nhập"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
          </div>

          <div className={styles.inputGroup}>
            <label className={styles.inputLabel}>Mật khẩu</label>

            <div className={styles.inputField}>
              <FaLock className={styles.FaUserLock} />

              <input
                className={styles.input}
                type="password"
                placeholder="Nhập mật khẩu"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          {error && <div className={styles.error}>{error}</div>}

          <button type="submit" className={styles.loginBtn}>
            Đăng nhập
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;

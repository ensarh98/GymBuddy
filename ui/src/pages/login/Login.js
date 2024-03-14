import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

import { HiOutlineMail } from "react-icons/hi";
import { FiLock } from "react-icons/fi";
import { IoCloseOutline } from "react-icons/io5";

export default function Login(props) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/login", {
        email: email,
        password: password,
      });

      props.setShowLogin(false);
      const { user, token } = response.data;
      Cookies.set("token", token, { expires: 1, path: "/" });
      navigate("/home");
      // Učitajte korisničku sesiju ili preusmjerite na početnu stranicu
    } catch (error) {
      console.error("Error during login:", error);
    }
  };

  return (
    props.showLogin && (
      <div className="login_container show">
        <div className="form_container">
          <i
            className="form_close"
            onClick={() => {
              props.setShowLogin(false);
              setEmail("");
              setPassword("");
            }}
          >
            <IoCloseOutline />
          </i>

          <div className="form login_form">
            <form action="#">
              <h2>Login</h2>

              <div className="input_box">
                <input
                  onChange={handleEmail}
                  value={email}
                  type="email"
                  placeholder="Email"
                  name="email"
                  required
                />
                <i className="email">
                  <HiOutlineMail />
                </i>
              </div>
              <div className="input_box">
                <input
                  onChange={handlePassword}
                  value={password}
                  type={"password"}
                  placeholder="Password"
                  name="password"
                  required
                />
                <i className="password">
                  <FiLock />
                </i>
              </div>

              <div className="option_field">
                <span className="checkbox">
                  <input type="checkbox" id="check" />
                  <label for="check">Remember me</label>
                </span>
                <a href="#" className="forgot_pw">
                  Forgot password?
                </a>
              </div>

              <button onClick={handleSubmit} className="button" type="submit">
                Login Now
              </button>

              <div className="register_login">
                Don't have an account?{" "}
                <a
                  href="#"
                  id="register"
                  onClick={() => {
                    props.setShowRegister(true);
                    props.setShowLogin(false);
                    setPassword("");
                    setEmail("");
                  }}
                >
                  Register
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>
    )
  );
}

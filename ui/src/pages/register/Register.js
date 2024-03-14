import React, { useState } from "react";
import "./Register.css";
import axios from "axios";

import { FaRegUser } from "react-icons/fa6";
import { HiOutlineMail } from "react-icons/hi";
import { FiLock } from "react-icons/fi";
import { IoCloseOutline } from "react-icons/io5";

export default function Register(props) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleFirstName = (e) => {
    setFirstName(e.target.value);
  };

  const handleLastName = (e) => {
    setLastName(e.target.value);
  };

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(
        "http://localhost:8080/register",
        {
          firstName,
          lastName,
          email,
          password,
        },
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then(function (response) {
        console.log(response);
        props.setShowRegister(false);
        //window.location.href = "/";
      })
      .catch(function (response) {
        console.log(response);
      });
  };

  return (
    props.showRegister && (
      <div className="register_container show">
        <div className="form_container">
          <i
            className="form_close"
            onClick={() => {
              props.setShowRegister(false);
              setFirstName("");
              setLastName("");
              setEmail("");
              setPassword("");
            }}
          >
            <IoCloseOutline />
          </i>

          <div className="form signup_form">
            <form method="POST" onSubmit={handleSubmit}>
              <h2>Signup</h2>
              <div className="input_box">
                <input
                  onChange={handleFirstName}
                  value={firstName}
                  type="text"
                  placeholder="Ime"
                  name="firstName"
                  required
                />
                <i className="firstName">
                  <FaRegUser />
                </i>
              </div>
              <div className="input_box">
                <input
                  onChange={handleLastName}
                  value={lastName}
                  type="text"
                  placeholder="Prezime"
                  name="lastName"
                  required
                />
                <i className="lastName">
                  <FaRegUser />
                </i>
              </div>
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
                  type="password"
                  placeholder="Password"
                  name="password"
                  required
                />
                <i className="password">
                  <FiLock />
                </i>
              </div>

              <button className="button" type="submit">
                Signup Now
              </button>

              <div className="register_login">
                Already have an account?{" "}
                <a
                  href="#"
                  id="login"
                  onClick={() => {
                    props.setShowRegister(false);
                    props.setShowLogin(true);
                    setFirstName("");
                    setLastName("");
                    setEmail("");
                    setPassword("");
                  }}
                >
                  Login
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>
    )
  );
}

import React from "react";

import { Link, useNavigate, useLocation } from "react-router-dom";

import "../css/Header.css";
import Cookies from "js-cookie";

export default function Header(props) {
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const handleLogout = (e) => {
    e.preventDefault();
    Cookies.remove("token");
    navigate("/");
  };

  return (
    <header className="header">
      <nav className="nav">
        <a href="/" className="nav_logo">
          GymBuddy
        </a>

        <ul className="nav_items">
          <li className="nav_item">
            {Cookies.get("token") != null && (
              <a
                href="/home"
                className={
                  pathname === "/home" ? "nav_link active" : "nav_link"
                }
              >
                Home
              </a>
            )}
            <a
              href="#"
              className={
                pathname === "/product" ? "nav_link active" : "nav_link"
              }
            >
              Product
            </a>
            <a
              href="#"
              className={
                pathname === "/services" ? "nav_link active" : "nav_link"
              }
            >
              Services
            </a>
            <a
              href="#"
              className={
                pathname === "/contact" ? "nav_link active" : "nav_link"
              }
            >
              Contact
            </a>
          </li>
        </ul>
        {Cookies.get("token") == null ? (
          <button className="button" onClick={() => props.setShowLogin(true)}>
            Login
          </button>
        ) : (
          <button className="button" onClick={handleLogout}>
            Logout
          </button>
        )}
      </nav>
    </header>
  );
}

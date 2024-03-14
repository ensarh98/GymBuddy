import { React, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

import "./HomePage.css";
import Header from "../index/components/Header";

export default function Home(props) {
  const navigate = useNavigate();

  useEffect(() => {
    const token = Cookies.get("token");
    if (!token) {
      navigate("/");
    }
  }, []);

  const handleLogout = (e) => {
    e.preventDefault();
    Cookies.remove("token");
    navigate("/");
  };

  return (
    <>
      <div className="homeContainer">
        <Header setShowLogin={props.setShowLogin} />
        <h1>TOKEN : {Cookies.get("token")}</h1>
      </div>
    </>
  );
}

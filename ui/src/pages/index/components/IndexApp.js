import React, { useState } from "react";
import Header from "./Header";
import Login from "../../login/Login";
import Register from "../../register/Register";
import Quote from "../../../shared_components/Quote";

export default function IndexApp() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);

  return (
    <section className="home">
      <Header setShowLogin={setShowLogin} />
      <Login
        showLogin={showLogin}
        setShowLogin={setShowLogin}
        setShowRegister={setShowRegister}
      />
      <Register
        setShowRegister={setShowRegister}
        setShowLogin={setShowLogin}
        showRegister={showRegister}
      />
      <Quote />
    </section>
  );
}

import React, { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import { useNavigate } from "react-router-dom";

type CurrentAdmin = {
  id: number;
  username: string;
};
type Drink = {
  id: string;
  name: string;
  price: string;
  count: number;
};

export const CoffeeAdminPage = () => {
  const { keycloak } = useKeycloak();
  const navigate = useNavigate();
  const [currentAdmin, setCurrentAdmin] = useState<CurrentAdmin>();
  const [drinks, setDrinks] = useState<Drink[]>();

  const updateScreen = () => {
    fetch("http://localhost:8080/servlet_war_exploded/current-admin", {
      method: "GET",
      headers: { Authorization: "Bearer " + keycloak.token },
    })
      .then((r) => r.json())
      .then(setCurrentAdmin)
      .catch(console.error);
    fetch("http://localhost:8080/servlet_war_exploded/drinks")
      .then((r) => r.json())
      .then(setDrinks)
      .catch(console.error);
  };

  useEffect(() => {
    setTimeout(() => {
      if (!keycloak.authenticated) {
        navigate("/");
        return;
      }
      updateScreen();
    }, 300);
  }, []);

  if (!currentAdmin || !drinks) {
    return <h1>Loading...</h1>;
  }

  return (
    <>
      <h1>
        {currentAdmin.username} (id = {currentAdmin.id})
      </h1>
      <button onClick={() => keycloak.logout()}>Logout</button>
      <table id="drinks">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Count exist</th>
            <th>Refresh</th>
          </tr>
        </thead>
        <tbody>
          {drinks.map((drink) => (
            <tr key={"tr_" + drink.id}>
              <td>{drink.name}</td>
              <td>{drink.price}</td>
              <td>{drink.count}</td>
              <td>
                <input type={"number"} id={"drink_" + drink.id} min={0} />
                <button
                  onClick={() => {
                    // @ts-ignore
                    const count = document.getElementById(
                      "drink_" + drink.id
                      // @ts-ignore
                    ).value;
                    fetch(
                      "http://localhost:8080/servlet_war_exploded/refresh-drink",
                      {
                        method: "PUT",
                        headers: {
                          Authorization: "Bearer " + keycloak.token,
                          "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                          drinkId: drink.id,
                          count: count,
                        }),
                      }
                    )
                      .then(() => updateScreen())
                      .catch(console.error);
                  }}
                >
                  Add
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

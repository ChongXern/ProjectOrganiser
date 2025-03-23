import {React} from "react";
import styles from './NavBar.module.css';

import {getImageUrl, isImageValid} from "../../utils";

export const NavBar = () => {
    return <nav className={styles.navBar}>
        <div className={styles.title}>
            <a href="/"> 
                <img src={getImageUrl('mainLogo.png')} alt="Proj Org Logo" />
            </a>
        </div>
        <div className={styles.rightItems}>
            <div className={styles.searchBar}>
                <input type="text" placeholder="Search Projects" name="search" />
                <button type="submit">
                    <img src={getImageUrl('navBar/searchIcon.png')} alt="Search Icon" />
                </button>
            </div>
            <button className={styles.profile}>
                <img src={getImageUrl('navBar/profileImages/default_profile_3.png')} 
                alt="Profile Image" className={styles.profileImage}/>
            </button>
        </div>
    </nav>
};

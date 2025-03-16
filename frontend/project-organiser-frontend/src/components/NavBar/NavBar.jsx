import {React, useState, useEffect} from "react";
import styles from './NavBar.module.css';

import {getImageUrl, isImageValid} from "../../utils";

export const NavBar = () => {
    const [imageUrl, setImageUrl] = useState('');
    const [isValid, setIsValid] = useState(false);

    useEffect(() => {
        const path = 'navBar/searchIcon.png';
        const url = getImageUrl(path);

        const checkImage = async () => {
            const valid = await isImageValid(url);
            setIsValid(valid);
            setImageUrl(valid ? url : getImageUrl('default-image.png'));
        };

        checkImage();
    }, []);
    return <nav className={styles.NavBar}>
        <div className={styles.titleContainer}>
            <a className={styles.title} href="/"> ProjOrg </a>
        </div>
        <div className={styles.rightItems}>
            <div className={styles.searchBar}>
                <input type="text" placeholder="Search Projects" name="search" />
                <button type="submit">
                    <img src={getImageUrl('navBar/searchIcon.png')} alt="Search Icon" />
                </button>
            </div>
            <div className={styles.profile}>
                <img src={getImageUrl('navBar/profileImages/default_profile_3.png')} 
                alt="Profile Image" className={styles.profileImage}/>
            </div>
        </div>
    </nav>
};

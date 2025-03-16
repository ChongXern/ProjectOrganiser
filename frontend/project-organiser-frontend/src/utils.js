export const getImageUrl = (path) => {
    try {
        const url = new URL(`/assets/${path}`, window.location.origin).href;
        return url;
    } catch (error) {
        console.error(`Error generating image URL for path "${path}":`, error);
        return new URL('/public/assets/default-image.png', import.meta.url).href;
    }
};

export const isImageValid = async (url) => {
    try {
        const response = await fetch(url, { method: 'HEAD' });
        return response.ok; // true if status is 200-299
    } catch (error) {
        console.error(`Error fetching image URL "${url}":`, error);
        return false;
    }
};

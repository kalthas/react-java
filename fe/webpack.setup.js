const fs = require('fs');
const path = require('path');

const file = path.resolve('./node_modules/babel-preset-react-app/index.js');
const text = fs.readFileSync(file, 'utf8');

if (!text.includes('babel-plugin-transform-decorators-legacy')) {
    if (text.includes('const plugins = [')) {
        let newText = text.replace(
            'const plugins = [',
            "const plugins = [\n  require.resolve('babel-plugin-transform-decorators-legacy'),",
        );
        fs.writeFileSync(file, newText, 'utf8');
    } else {
        throw new Error(`Failed to inject babel-plugin-relay.`);
    }
}
